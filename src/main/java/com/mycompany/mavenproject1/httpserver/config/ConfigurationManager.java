/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Singleton.java to edit this template
 */
package com.mycompany.mavenproject1.httpserver.config;

import com.fasterxml.jackson.databind.JsonNode;
import com.mycompany.mavenproject1.httpserver.util.Json;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author kimoo
 */
public class ConfigurationManager {
    
    private static Configuration currentConfiguration;
    private ConfigurationManager() {
    }
    
    public static ConfigurationManager getInstance() {
        return ConfigurationManagerHolder.INSTANCE;
    }
    
    private static class ConfigurationManagerHolder {

        private static final ConfigurationManager INSTANCE = new ConfigurationManager();
    }
    
    public void loadConfigurationFile(String filePath) throws HttpConfigurationException 
    {
        FileReader reader = null;
        try{
            reader = new FileReader(filePath);
        }
        catch(FileNotFoundException e)
        {
            throw new HttpConfigurationException(e);
        }
        StringBuffer buffer = new StringBuffer();
        int i;
        try
        {
            while( (i = reader.read()) != -1)
            {
                buffer.append((char)i);
            }
        }
        catch(IOException e)
        {
            throw new HttpConfigurationException(e);

        }
        JsonNode config = null;
        try{
            config = Json.parse(buffer.toString());
        }
        catch(IOException e)
        {
            throw new HttpConfigurationException("Error parsing config file", e);
        }
        try{
        currentConfiguration = Json.fromJson(config, Configuration.class);
        }
        catch(IOException e)
        {
            throw new HttpConfigurationException("Error parsing config file, internal", e);
        }
    }
    public Configuration getConfiguration() throws HttpConfigurationException
    {
        if(currentConfiguration == null)
            throw new HttpConfigurationException("No Configuration is set.");
        
        return currentConfiguration;
        
        
    }
}
