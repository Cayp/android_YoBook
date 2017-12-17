package com.example.book.Chat.keepalive;

import android.content.Context;
import android.util.Log;

import com.example.book.Chat.entity.Notice;
import com.example.book.Chat.utils.RxBus;
import com.example.book.Tools.Constant;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

import java.net.URLDecoder;

/**
 * Created by Clanner on 2017/2/17.
 */

public class ClientHandler extends IoHandlerAdapter {

    private Context mContext;

    public ClientHandler(Context context) {
        Log.e("MINA","MINA" );
        this.mContext = context;
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        super.sessionOpened(session);
        //将我们的session保存到我们的session manager类中，从而可以发送信息到服务器
        SessionManager.getInstance().setSession(session);
        Log.e("sessionOpened: ",session.toString());
    }

    @Override
    public void messageReceived(IoSession session, Object originMessage) throws Exception {
        String message = originMessage.toString();
        String checkString = message.substring(0,4);
        Log.e("MINA","MINA"+Constant.currentUserId+"SS"+checkString);
        if (mContext != null) {
            if (checkString.equals("1111")) {
                Log.e("MINA",checkString+Constant.currentUserId);
                session.write("1112" + ":" + Constant.currentUserId);
            } else {
                String origin = URLDecoder.decode(message, "utf-8");
                String[] arr = origin.split("-:-");
                Notice notice = new Notice(arr[0], arr[1]);
                RxBus.getInstance().post(notice);
            }
        }
    }
}
