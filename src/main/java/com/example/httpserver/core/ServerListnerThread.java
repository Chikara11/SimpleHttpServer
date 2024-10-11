package com.example.httpserver.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.httpserver.HttpServer;

public class ServerListnerThread extends Thread {

    private final static Logger LOGGER=LoggerFactory.getLogger(HttpServer.class); 


    private int port;
    private String webroot;
    private ServerSocket serverSocket;

    public ServerListnerThread(int port,String webroot) throws IOException{
        this.port = port;
        this.webroot = webroot;
        //Set the serverSocket to the port
        this.serverSocket = new ServerSocket(this.port);
    }

    @Override   
    public void run(){

        try {

            while (serverSocket.isBound() && !serverSocket.isClosed()) {
                //accept the connection
                Socket socket = serverSocket.accept();

                LOGGER.info(" * Connection accepted: " + socket.getInetAddress() );

                HttpConnectionWorkerThread workerThread = new HttpConnectionWorkerThread(socket);
                workerThread.start();

            }
            //serverSocket.close()todo handle close
        } catch (IOException e) {
            LOGGER.info("Problem with setting socket", e);
        } finally{
            if (serverSocket!=null) {
                try {
                    serverSocket.close();
                } catch (IOException ex) {}
            }
        }

    }

}
