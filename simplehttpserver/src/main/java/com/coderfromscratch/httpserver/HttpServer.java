package com.coderfromscratch.httpserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.coderfromscratch.httpserver.core.ServerListenerThread;
import config.Configuration;
import config.ConfigurationManager;

import java.io.IOException;

public class HttpServer {

    private static final Logger logger = LoggerFactory.getLogger(HttpServer.class);

    public static void main(String[] args) {
        logger.info("Server starting...");

        ConfigurationManager.getInstance().loadConfigurationFile("src/main/resources/http.json");
        Configuration conf = ConfigurationManager.getInstance().getCurrentConfiguration();

        logger.info("Using Port: " + conf.getPort());
        logger.info("Using webRoot: " + conf.getWebroot());

        try {
            ServerListenerThread serverListenerThread = new ServerListenerThread(conf.getPort(), conf.getWebroot());
            serverListenerThread.start();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
