package com.example.httpserver.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

public class json {

    private static ObjectMapper myObjectMapper = defaulObjectMapper();

    /*This method defines and returns a configured instance of Jackson's ObjectMapper,
    which is a central class for handling JSON serialization and deserialization in Jackson */

    public static ObjectMapper defaulObjectMapper(){
        ObjectMapper om = new ObjectMapper();
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return om;
    }

    /* This method is a static utility function designed to parse a JSON string and return a corresponding
    representation as a JsonNode object using Jackson's ObjectMapper  */

    public static JsonNode parse(String jsonSrc) throws IOException{
        return myObjectMapper.readTree(jsonSrc);
    }

    /*This method is designed to convert a JsonNode into an instance of a specified Java class (clazz)  */
    public static <A> A fromJson(JsonNode node, Class<A> clazz) throws JsonProcessingException
    {
        return myObjectMapper.treeToValue(node, clazz);
    }

    /* This convert a Java object into a JsonNode */
    public static JsonNode toJson(Object obj){
        return myObjectMapper.valueToTree(obj);
    }

    /* These methods provide a way to convert JsonNode objects into JSON strings */
    public static String stringg(JsonNode node) throws JsonProcessingException{
        return generateJson(node, false);
    }

    public static String stringgPretty(JsonNode node) throws JsonProcessingException{
        return generateJson(node, true);
    }

    private static String generateJson(Object o, boolean pretty) throws JsonProcessingException{
        ObjectWriter objectWriter = myObjectMapper.writer();
        if (pretty) {
            objectWriter = objectWriter.with(SerializationFeature.INDENT_OUTPUT);
        }
        return objectWriter.writeValueAsString(o);
    }


}
