package com.example.book.Chat.keepalive;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.HandlerThread;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.book.Tools.MyApplication;

/**
 * Created by Clanner on 2017/2/6.
 */

public class ConnectionService extends Service {
    private static final String TAG = "ConnectionService";
    private ConnectionThread thread;
    private final String ADDRESS = "139.199.165.150";
    private final int PORT = 2916;

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

        public ConnectionThread(String name, Context context) {
            super(name);
            this.context = context;
            ConnectionConfig config = new ConnectionConfig.Builder(context)
                    .setIp(ADDRESS)
                    .setPort(PORT)
                    .setReadBufferSize(10240)
                    .setConnectionTimeout(10000).build();
            manager = new ConnectionManager(config);
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
}
