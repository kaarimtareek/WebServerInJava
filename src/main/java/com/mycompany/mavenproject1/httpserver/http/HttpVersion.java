/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1.httpserver.http;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author kimoo
 */
public enum HttpVersion {
    HTTP_1_1("HTTP/1.1", 1, 1);
    
    public final String Literal;
    public final int Major;
    public final int Minor;
    
    HttpVersion(String literal, int major, int minor)
    {
        Literal = literal;
        Major = major;
        Minor = minor;
    }
    private static final Pattern httpVersionPattern = Pattern.compile("^HTTP/(?<major>\\d+).(?<minor>\\d+)"); 
    public static HttpVersion getBestCompatibleVersion(String literal) throws BadHttpVersionException
    {
        Matcher matcher = httpVersionPattern.matcher(literal);
        if(!matcher.find() || matcher.groupCount() != 2)
        {
            throw new BadHttpVersionException();
        }
        int major =Integer.parseInt( matcher.group("major"));
        int minor =Integer.parseInt( matcher.group("minor"));
        HttpVersion tempVersion = null;
        for(HttpVersion version : values())
        {
            if(version.Literal.equals(literal))
                return version;
            
            if(major == version.Major)
            {
                if(version.Minor < minor)
                {
                    tempVersion = version;
                }
            }
        }          
        return tempVersion;
    }
}
