package com.example.book.Chat.keepalive;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.HandlerThread;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.book.Chat.activity.ChatActivity;
import com.example.book.Chat.entity.Notice;
import com.example.book.Chat.utils.AppUtil;
import com.example.book.Chat.utils.RxBus;
import com.example.book.EntityClass.ChatBook;
import com.example.book.EntityClass.ChatObjects;
import com.example.book.R;
import com.example.book.Tools.Constant;
import com.example.book.Tools.MyApplication;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.Subscription;

/**
 * Created by Clanner on 2017/2/6.
 */

public class ConnectionService extends Service {
    private static final String TAG = "ConnectionService";
    private ConnectionThread thread;
    private final String ADDRESS = "119.29.143.189";
    private final int PORT = 2918;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "isStartService");
        thread = new ConnectionThread("mina", MyApplication.getContext());
        thread.start();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        thread.disConnection();
        thread = null;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * 用来负责调用ConnectionManager类来完成与服务器的连接
     */
    private class ConnectionThread extends HandlerThread {

        private Context context;
        private boolean isConnection;
        private ConnectionManager manager;
        private Subscription subscription;
        public ConnectionThread(String name, Context context) {
            super(name);
            this.context = context;
            ConnectionConfig config = new ConnectionConfig.Builder(context)
                    .setIp(ADDRESS)
                    .setPort(PORT)
                    .setReadBufferSize(10240)
                    .setConnectionTimeout(10000).build();
            manager = new ConnectionManager(config);
            subscription = RxBus.getInstance().receive(Notice.class).subscribe(new Subscriber<Notice>() {
                @Override
                public void onCompleted() {
                    Log.e("DEBUG", "完成");
                }

                @Override
                public void onError(Throwable e) {
                    Log.e("DEBUG", "错误信息" + e.getMessage());
                }

                @Override
                public void onNext(Notice notice) {
                    Log.e("onNext: ","o1" );
                    String toUserId = notice.getFrom();
                    ChatBook chatBook = new ChatBook();
                    chatBook.setContent(notice.getContent());
                    chatBook.setToUserId(Integer.parseInt(notice.getFrom()));
                    checkChatList(toUserId);
                    setNotification(Integer.parseInt(notice.getFrom()),notice.getContent());
                    AppUtil.saveOneChatRecord(toUserId,notice.getContent(),Constant.CHAT_LEFT);
                    if(!AppUtil.islistExist(toUserId)){
                        ChatObjects chatObjects = new ChatObjects();
                        chatObjects.setToUserId(Integer.parseInt(toUserId));
                        chatObjects.save();                      //储存聊天列表
                    }
                }
            });
        }

        /**
         * 开始连接我们的服务器
         */
        @Override
        protected void onLooperPrepared() {
            for (; ; ) {
                isConnection = manager.connect();//完成服务器的连接
                Log.e("MINA", String.valueOf(isConnection));
                if (isConnection) {
                    Log.e("MINA", "连接成功");
                    break;
                }
                try {
                    Thread.sleep(3000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        public void disConnection() {
            manager.disConnection();//完成与服务器的断开操作
        }
    }
    private void setNotification(int toUserId,String content){
        Intent intent = new Intent(MyApplication.getContext(), ChatActivity.class);
        intent.putExtra("flag",3);
        intent.putExtra("toUserId",toUserId);
        PendingIntent pendingIntent = PendingIntent.getActivity(MyApplication.getContext(),0,intent,0);
        NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        Notification notification = new NotificationCompat.Builder(MyApplication.getContext())
                                        .setContentTitle("你收到信息")
                                        .setTicker("你收到了消息")
                                        .setContentText(content)
                                        .setSmallIcon(R.drawable.icon)
                                        .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.icon))
                                        .setWhen(System.currentTimeMillis())
                                        .setContentIntent(pendingIntent)
                                        .setAutoCancel(true)
                                        .build();
        manager.notify(1,notification);
    }
    private void checkChatList(String userId){
        List<ChatObjects> chatObjectses = DataSupport.where("toUserId = ?",userId).find(ChatObjects.class);
        List<ChatObjects> chatObjectses1 = new ArrayList<>();
        chatObjectses1.addAll(chatObjectses);
        if (chatObjectses1.size()== 0){
            ChatObjects chatObjects = new ChatObjects();
            chatObjects.setToUserId(Integer.parseInt(userId));
            chatObjects.save();
        }
    }
}
