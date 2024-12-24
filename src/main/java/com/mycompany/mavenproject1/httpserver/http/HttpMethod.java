/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1.httpserver.http;

/**
 *
 * @author kimoo
 */
public enum HttpMethod {
    GET, HEAD;
    public static final int MAX_LENGTH;
    static{
        int tempMax = -1;
        for(HttpMethod method : values())
        {
            if(method.name().length()  > tempMax)
            {
                tempMax = method.name().length();
            }
        }
        MAX_LENGTH = tempMax;
    }
}
