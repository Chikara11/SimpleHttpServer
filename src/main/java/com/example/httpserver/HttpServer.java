//https://www.youtube.com/watch?v=n3tDl1JsJiE&list=PLAuGQNR28pW56GigraPdiI0oKwcs8gglW&index=4

package com.example.httpserver;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.httpserver.config.ConfigurationManager;
import com.example.httpserver.core.ServerListnerThread;

/* 
 * Driver class for the Http Server 
*/

public class HttpServer {


    private final static Logger LOGGER=LoggerFactory.getLogger(HttpServer.class); 

    public static void main(String[] args)
    {
        LOGGER.info("Server starting...");

        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
        com.example.httpserver.config.Configuration conf = ConfigurationManager.getInstance().getCurrentconfiguration();

        LOGGER.info("Using Port" + conf.getPort());
        LOGGER.info("Using Port" + conf.getWebroot());

        ServerListnerThread serverListnerThread;
        try {
            serverListnerThread = new ServerListnerThread(conf.getPort(), conf.getWebroot());
            serverListnerThread.start();
        } catch (IOException ex) {
            //TODO handle later
        }
        

    }

}
