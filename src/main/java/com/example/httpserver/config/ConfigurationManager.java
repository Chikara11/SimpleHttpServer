package com.example.httpserver.config;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.example.httpserver.util.json;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

public class ConfigurationManager {

    private static ConfigurationManager myConfigurationManager;
    private static Configuration myCurrentConfiguration;

    private ConfigurationManager(){
    }

    public static ConfigurationManager getInstance(){
        if (myConfigurationManager==null){
            myConfigurationManager = new ConfigurationManager();  
        }
        return myConfigurationManager; 
    }


    /*
     * Used to load a configuration file by the path provided
     */
    public void loadConfigurationFile(String filePath) {
        FileReader fileReader;
        try {
            fileReader = new FileReader(filePath);
        } catch (FileNotFoundException e) {
            throw new HttpConfigurationException(e);
        }
        StringBuffer sb = new StringBuffer();
        int i;
        try {
            while ((i = fileReader.read()) != -1) {
                sb.append((char)i);
            }
        } catch (IOException e) {
            throw new HttpConfigurationException(e);
        }
        JsonNode conf;
        try {
            conf = json.parse(sb.toString());
        } catch (IOException e) {
            throw new HttpConfigurationException("Error parsing the C onfiguration File",e);
        }
        try {
            myCurrentConfiguration = json.fromJson(conf, Configuration.class);
        } catch (JsonProcessingException ex) {
            throw new HttpConfigurationException("Error parsing the Configuration File, internal",ex);
        }
    }


    /*
     * Returns the current loaded Configuration
     */
    public Configuration getCurrentconfiguration (){
        if (myCurrentConfiguration == null){
            throw new HttpConfigurationException("No Current Configuration Set.");
        }
        return myCurrentConfiguration;
    }

}
