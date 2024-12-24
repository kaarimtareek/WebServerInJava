/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1.httpserver.http;

/**
 *
 * @author kimoo
 */
public enum HttpStatusCode {
    //CLIENT ERRORS
    CLIENT_ERROR_400_BAD_RQUEST (400, "Bad request"),
    CLIENT_ERROR_401_METHOD_NOT_ALLOWED (401, "Method not allowed"),
    CLIENT_ERROR_414_BAD_RQUEST (414, "URI too long"),
    //SERVER ERRORS
       
    SERVER_ERROR_500_INTERNAL_SERVER_ERROR (500, "Internal server error"),
    SERVER_ERROR_501_NOT_IMPLEMENTED (501, "Not implemeneted"),
    SERVER_ERROR_505_HTTP_VERSION_NOT_SUPPORTED(505,"Http verssion not supported");

    public final int statusCode;
    public final String message;

    private HttpStatusCode(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
    
}
