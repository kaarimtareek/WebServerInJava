/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1.httpserver;

import com.mycompany.mavenproject1.httpserver.core.HttpConnectionWorker;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author kimoo
 */
public class ServerListenerThread extends Thread {
    
    private final static Logger LOGGER =  LoggerFactory.getLogger(ServerListenerThread.class);

    private int port;
    private String webroot;
    private ServerSocket serverSocket;
    public int getPort() {
        return port;
    }

    public String getWebroot() {
        return webroot;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setWebroot(String webroot) {
        this.webroot = webroot;
    }

    public ServerListenerThread(int port, String webroot) throws IOException {
        this.port = port;
        this.webroot = webroot;
        this.serverSocket = new ServerSocket(port);
    }
    
    @Override
    public void run() {
        
        try{
            while(serverSocket.isBound() && !serverSocket.isClosed())
            {
                Socket socket = serverSocket.accept();
                LOGGER.info("Connection Accepted: "+ socket.getInetAddress());
                HttpConnectionWorker worker = new HttpConnectionWorker(socket);
                worker.start();
               }
            //TODO handle close
            //serverSocket.close(); 
        }
        catch(IOException exception)
        {
            exception.printStackTrace();
            if(serverSocket !=null)
            {
                try {
                    serverSocket.close();
                } catch (IOException ex) {
                }
            }
            
        }
        
    }
    
}
