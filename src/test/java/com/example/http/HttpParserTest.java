package com.example.http;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
public class HttpParserTest {

    private HttpParser httpParser;
    
    @BeforeAll
    public void beforeClass(){
        httpParser = new HttpParser();
    }


    @Test
    public void ParseHttpRequest() {
        HttpRequest request = null;
        request = httpParser.parseHttpRequest(generatevalidGETTestCase());

        assertEquals(request.getMethod(),HttpMethod.GET);
    }

    @Test
    public void ParseHttpRequestBadMethod1() {
        HttpRequest request = httpParser.parseHttpRequest(generateBadTestCaseMethodName1());
        
        
    }

    


    private InputStream generatevalidGETTestCase(){
        String rawData ="GET / HTTP/1.1\r\n" + //
                        "Host: localhost:8081\r\n" + //
                        "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:130.0) Gecko/20100101 Firefox/130.0\r\n" + //
                        "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/png,image/svg+xml,*/*;q=0.8\r\n" + //
                        "Accept-Language: en-US,en;q=0.5\r\n" + //
                        "Accept-Encoding: gzip, deflate, br, zstd\r\n" +
                        "\r\n";

        InputStream inputStream = new ByteArrayInputStream(
            rawData.getBytes(
                StandardCharsets.US_ASCII
            )
        );
        return inputStream;
    }

    private InputStream generateBadTestCaseMethodName1(){
        String rawData ="GET / HTTP/1.1\r\n" + //
                        "Host: localhost:8081\r\n" + //
                        "Accept-Language: en-US,en;q=0.5\r\n" + //
                        "\r\n";

        InputStream inputStream = new ByteArrayInputStream(
            rawData.getBytes(
                StandardCharsets.US_ASCII
            )
        );
        return inputStream;
    }

    private InputStream generateBadTestCaseMethodName2(){
        String rawData ="GET / HTTP/1.1\r\n" + //
                        "Host: localhost:8081\r\n" + //
                        "Accept-Language: en-US,en;q=0.5\r\n" + //
                        "\r\n";

        InputStream inputStream = new ByteArrayInputStream(
            rawData.getBytes(
                StandardCharsets.US_ASCII
            )
        );
        return inputStream;
    }
}
