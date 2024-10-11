//Your HttpParser class is designed to parse HTTP requests from an InputStream


package com.example.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpParser {

    private final static Logger LOGGER  = LoggerFactory.getLogger(HttpParser.class);


    private static final int SP = 0x20; // 32
    private static final int CR = 0x0D; // 13
    private static final int LF = 0x0A; // 10

    //This method reads the HTTP request from the input stream and attempts to parse the request line, headers, and body.
    public HttpRequest parseHttpRequest(InputStream inputStream){
        InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.US_ASCII);

        HttpRequest request = new HttpRequest();

        try {
            parseRequestLine(reader,request);
        } catch (IOException ex) {
        }
        parseHeaders(reader,request);
        parseBody(reader,request);

        return request;
    }


    //This method is responsible for parsing the request line, identifying the HTTP method and request target.
    private void parseRequestLine(InputStreamReader reader, HttpRequest request) throws IOException,HttpParsingException {
        StringBuilder processingDataBuffer = new StringBuilder();

        boolean methodParsed = false;
        boolean requesTargetParsed = false;

        int _byte;
        while ((_byte=reader.read())>=0) {
            if(_byte == CR){
                _byte=reader.read();
                if (_byte == LF) {
                    LOGGER.debug("Request line VERSION to process : {}", processingDataBuffer.toString());

                    return;
                }
            }

            if(_byte == SP){
                if (!methodParsed) {
                    LOGGER.debug("Request line METHOD to process : {}", processingDataBuffer.toString());
                    request.setMethod(processingDataBuffer.toString());
                    methodParsed = true;
                } else if (!requesTargetParsed) {
                    LOGGER.debug("Request line REQ TARGET to process : {}", processingDataBuffer.toString());
                    requesTargetParsed = true;
                }
                processingDataBuffer.delete(0,processingDataBuffer.length());
                 
            }else{
                processingDataBuffer.append((char)_byte);
                if(!methodParsed){
                    if (processingDataBuffer.length() > HttpMethod.MAX_LENGTH) {
                        throw new HttpParsingException(HttpStatusCode.SERVER_ERROR_501_NOT_IMPLEMENTED);
                    }
                }
            }

        }
    }

    private void parseHeaders(InputStreamReader reader, HttpRequest request) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    

    private void parseBody(InputStreamReader reader, HttpRequest request) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    

}
