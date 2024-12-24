/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1.httpserver;

import com.mycompany.mavenproject1.httpserver.config.Configuration;
import com.mycompany.mavenproject1.httpserver.config.ConfigurationManager;
import com.mycompany.mavenproject1.httpserver.config.HttpConfigurationException;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 * @author kimoo
 */
public class HttpServer {
    
    private final static Logger LOGGER =  LoggerFactory.getLogger(HttpServer.class);
    
    public static void main(String[] args) throws HttpConfigurationException
    {
        LOGGER.info("Server Starting");
        String configPath = "src/main/resources/http.json";
        ConfigurationManager configManager = ConfigurationManager.getInstance();
        configManager.loadConfigurationFile(configPath);
        Configuration config = configManager.getConfiguration();
        int port = config.getPort();
        String webRoot = config.getWebroot();
        LOGGER.info("Using port:"+ port );

        ServerListenerThread thread;
        try {
            thread = new ServerListenerThread(port, webRoot);
            thread.start();
        } catch (IOException ex) {
            LOGGER.error(ex.toString());
        }
        LOGGER.info("closing server");
    }
}
