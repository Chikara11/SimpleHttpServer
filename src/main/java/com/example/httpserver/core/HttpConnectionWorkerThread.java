package com.example.httpserver.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.httpserver.HttpServer;

public class HttpConnectionWorkerThread extends Thread{

    private final static Logger LOGGER=LoggerFactory.getLogger(HttpServer.class); 

    private Socket socket;
    public HttpConnectionWorkerThread (Socket socket){
        this.socket = socket;
    }

    @Override
    public void run(){
        InputStream inputStream =null;
        OutputStream outputStream = null;
        try {
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();

            String html = "<html><head><title>Java HTTP Server</title></head><body><h1>This page was served using my Java HTTp Server</h1></body></html>";

            final String CRLF = "\n\r"; //13, 10

            String response = 
            "HTTP/1.1 200 OK" + CRLF + //Status Line :HTTP_VERSION RESPONSE_CODE RESPONSE_MESSAGE 
            "Content-Length:" + html.getBytes().length + CRLF +  //HEADER
            CRLF + 
            html + 
            CRLF + CRLF;

            outputStream.write(response.getBytes());

    
            LOGGER.info("Connection Processiing Finished");

        } catch (IOException e) {
            LOGGER.info("Problem with cummunication",e);
        } finally{
            if(inputStream!=null){
                try {
                    inputStream.close();
                } catch (IOException ex) {}
            }
            if(outputStream!=null){
                try {
                    outputStream.close();
                } catch (IOException ex) {}
            }
            if(socket!=null){
                try {
                    socket.close();
                } catch (IOException ex) {}
            }
        }
        
    }

}
