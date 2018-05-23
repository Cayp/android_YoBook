package com.example.book.Chat.keepalive;

import android.content.Context;

/**
 * Created by Clanner on 2017/2/6.
 * 一个Builder模式
 */

public class ConnectionConfig {

    private ConnectionConfig(){}

    private Context context;
    private String ip;
    private int port;
    private int readBufferSize;
    private long connectionTimeout;

    public long getConnectionTimeout() {
        return connectionTimeout;
    }

    public Context getContext() {
        return context;
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }

    public int getReadBufferSize() {
        return readBufferSize;
    }

    public static class Builder{
        private Context context;
        private String ip="119.29.143.189";
        private int port = 8080;
        private int readBufferSize = 10240;
        private long connectionTimeout = 10000;

        public Builder(Context context){
            this.context = context;
        }

        public Builder setIp(String ip){
            this.ip = ip;
            return this;
        }

        public Builder setPort(int port){
            this.port = port;
            return this;
        }

        public Builder setReadBufferSize(int readBufferSize){
            this.readBufferSize = readBufferSize;
            return this;
        }

        public Builder setConnectionTimeout(long connectionTimeout){
            this.connectionTimeout = connectionTimeout;
            return this;
        }

//        private void applyConfig(ConnectionConfig config){
//            config.context = this.context;
//            config.ip = this.ip;
//            config.port = this.port;
//            config.connectionTimeout = connectionTimeout;
//        }

        public ConnectionConfig build(){
            ConnectionConfig config = new ConnectionConfig();
            config.context = this.context;
            config.ip = this.ip;
            config.port = this.port;
            config.connectionTimeout = this.connectionTimeout;
            config.readBufferSize = this.readBufferSize;
            return config;
        }
    }
}
