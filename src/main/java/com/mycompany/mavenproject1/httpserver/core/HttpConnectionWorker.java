/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1.httpserver.core;

import com.mycompany.mavenproject1.httpserver.HttpServer;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author kimoo
 */
public class HttpConnectionWorker  extends Thread{
    private Socket socket;
    private final static Logger LOGGER =  LoggerFactory.getLogger(HttpConnectionWorker.class);

    public HttpConnectionWorker(Socket socket) {
        this.socket = socket;
    }
    @Override
    public void run() {
         InputStream inputStream = null;
         OutputStream outputStream = null;
        try
        {
                
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
            //prase the request 
            
            int read;
//            StringBuffer readBuffer = new StringBuffer();
//            while( (read = inputStream.read()) >= 0)
//                readBuffer.append((char)read);
//                
            String html = "<html><head><title>Simple http server</title></head> <body> <h1> This is served by simple http server </h1> </body> </html>";
            //HTTP response format
            //
            //1- STATUS LINE =>  HTTP_VERSION RESPONSE_CODE RESPONSE_MESSAGE + SEPARATOR (\n\r)
            //2- HEADERS => Content-Length: (size of the message) + SEPARATOR (\n\r)
            String statusLine = "HTTP/1.1 200 OK";

            final String CRLF = "\n\r";

            int messageSize = html.getBytes().length;
            String headers = "Content-Type: text/html; charset=utf-8" + "Content-Length: " + messageSize ;

            String response = statusLine + CRLF
                    + headers + CRLF+ CRLF 
                    + html
                     ;
            outputStream.write(response.getBytes());

            inputStream.close();
            outputStream.close();
            socket.close();
            LOGGER.info("Connection processing finished");
        }
        catch(IOException exception)
        {
            exception.printStackTrace();
            if(inputStream != null)
                try {
                    inputStream.close();
            } catch (IOException ex) {
            }
            if(outputStream !=null)
                try {
                    outputStream.close();
                    //TODO HANDLE LATER
            } catch (IOException ex) {
            }
            if(socket != null)
                try {
                    socket.close();
            } catch (IOException ex) {
            }
        }
    }
    
}
