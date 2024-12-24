/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1.httpserver.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.IOException;

/**
 *
 * @author kimoo
 */
public class Json {
    private static ObjectMapper objectMapper = defaultObjectMapper();
 
    private static ObjectMapper defaultObjectMapper(){
        ObjectMapper om = new ObjectMapper();
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return om;
    }
    
    public static JsonNode parse(String src) throws IOException
    {
        return objectMapper.readTree(src);
    }
    
    public static <T> T fromJson(JsonNode node, Class<T> claz) throws JsonProcessingException
    {
        return objectMapper.treeToValue(node, claz);
    }
    public static JsonNode toJson(Object obj)
    {
        return objectMapper.valueToTree(obj);
    }
    private static String generateJson(Object obj, boolean pretty) throws JsonProcessingException
    {
        ObjectWriter writer = objectMapper.writer();
        if(pretty)
            writer = writer.with(SerializationFeature.INDENT_OUTPUT);
        return writer.writeValueAsString(obj);
    }
    public static String stringify (JsonNode node) throws JsonProcessingException 
    {
        return generateJson(node, false);
    }
    public static String stringifyPretty (JsonNode node) throws JsonProcessingException
    {
        return generateJson(node, true);
    }
    
}
