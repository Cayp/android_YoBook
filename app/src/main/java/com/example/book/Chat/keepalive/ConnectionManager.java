package com.example.book.Chat.keepalive;

import android.content.Context;
import android.util.Log;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.lang.ref.WeakReference;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * Created by Clanner on 2017/1/31.
 */

public class ConnectionManager {

    private static IoSession session;
    private ConnectionConfig config;
    private WeakReference<Context> context;
    private NioSocketConnector connector;
    private InetSocketAddress address;

    public ConnectionManager(ConnectionConfig config) {
        this.config = config;
        this.context = new WeakReference<Context>(config.getContext());
        init();
    }

    public static IoSession getSession() {
        return session == null ? null : session;
    }

    /**
     * 初始化
     */
    private void init() {
        Log.e("MINA", "初始化配置");
        address = new InetSocketAddress(config.getIp(), config.getPort());
        connector = new NioSocketConnector();
        connector.setConnectTimeoutMillis(30000);
        connector.getSessionConfig().setReceiveBufferSize(config.getReadBufferSize());
        connector.getFilterChain().addLast("logger", new LoggingFilter());
        connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(
                new TextLineCodecFactory(Charset.forName("utf-8"))));
        connector.setHandler(new ClientHandler(context.get()));
        connector.setDefaultRemoteAddress(address);
    }

    /**
     * 外层调用取得与服务器连接连接
     */
    public boolean connect() {
        try {
            Log.e("MINA", "开始连接我们的服务器");
            ConnectFuture future = connector.connect();
            future.awaitUninterruptibly();
            session = future.getSession();
        } catch (Exception e) {
            Log.e("MINA", "连接失败");
            return false;
        }
        return session == null ? false : true;
    }

    /**
     * 断开连接的方法
     */
    public void disConnection() {
        connector.dispose();
        connector = null;
        session = null;
        address = null;
        context = null;
    }
}
