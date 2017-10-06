package com.example.book.Chat.keepalive;

import org.apache.mina.core.session.IoSession;

/**
 * Created by Clanner on 2017/2/6.
 */

public class SessionManager {

    private static SessionManager instance = null;
    /**
     * 最终与服务器进行通信的对象
     */
    private IoSession session;

    private SessionManager() {
    }

    public static SessionManager getInstance() {

        if (instance == null) {
            synchronized (SessionManager.class) {
                if (instance == null) {
                    instance = new SessionManager();
                }
            }
        }
        return instance;
    }

    public void setSession(IoSession session) {
        this.session = session;
    }

    public void closeSession() {
        if (session != null) session.close();
    }

    public void removeSession() {
        this.session = null;
    }
}
