/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.mavenproject1.httpserver.http;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

/**
 *
 * @author kimoo
 */
@TestInstance(Lifecycle.PER_CLASS)
public class HttpParserTest {
    private HttpParser httpParser;
    public HttpParserTest() {
    }

    /**
     * Test of parseHttpRequest method, of class HttpParser.
     */
    
    @BeforeAll
    public void before(){
        httpParser = new HttpParser();
    }
    @Test
    public void testParseHttpRequest() {
        
        // TODO review the generated test code and remove the default call to fail.
        HttpRequest request = null;
        try {
              
            request = httpParser.parseHttpRequest(generateValidGetTestCase());
        } catch (HttpParsingException ex) {
            fail( ex);
        }
            
        assertNotNull(request);
        assertEquals(request.getMethod(), HttpMethod.GET );
        assertEquals(request.getRequestTarget(), "/");
        //the requset consists of 3 sections, request line, headers & body
    }
    @Test
    public void testParseHttpRequestToFail() {
        
        try {
            // TODO review the generated test code and remove the default call to fail.
            HttpRequest request = httpParser.parseHttpRequest(generateInValidGetTestCase());
            
            //assertth(request.getMethod(), HttpMethod.GET );
            //fail("The test case is a prototype.");
            //the requset consists of 3 sections, request line, headers & body
        } catch (HttpParsingException ex) {
            System.out.println("Test failed successfuly");
            assertEquals(ex.errorCode, HttpStatusCode.SERVER_ERROR_501_NOT_IMPLEMENTED);
        }
    }
    @Test
    public void testParseHttpRequestToFailTooLong() {
        
        try {
            // TODO review the generated test code and remove the default call to fail.
            HttpRequest request = httpParser.parseHttpRequest(generateInValidTooLongGetTestCase());
            
            //assertth(request.getMethod(), HttpMethod.GET );
            //fail("The test case is a prototype.");
            //the requset consists of 3 sections, request line, headers & body
        } catch (HttpParsingException ex) {
            assertEquals(ex.errorCode, HttpStatusCode.CLIENT_ERROR_401_METHOD_NOT_ALLOWED);
        }
    }
    
    @Test
    public void testParseHttpRequestToFailMoreElements() {
        
        try {
            // TODO review the generated test code and remove the default call to fail.
            HttpRequest request = httpParser.parseHttpRequest(generateInValidMoreElementsGetTestCase());
            
            //assertth(request.getMethod(), HttpMethod.GET );
            //fail("The test case is a prototype.");
            //the requset consists of 3 sections, request line, headers & body
        } catch (HttpParsingException ex) {
            assertEquals(ex.errorCode, HttpStatusCode.CLIENT_ERROR_400_BAD_RQUEST);
        }
    }
        @Test
    public void testParseHttpRequestToFailEmptyRequest() {
        
        try {
            // TODO review the generated test code and remove the default call to fail.
            HttpRequest request = httpParser.parseHttpRequest(generateInValidEmptyGetTestCase());
            
            //assertth(request.getMethod(), HttpMethod.GET );
            //fail("The test case is a prototype.");
            //the requset consists of 3 sections, request line, headers & body
        } catch (HttpParsingException ex) {
            assertEquals(ex.errorCode, HttpStatusCode.CLIENT_ERROR_400_BAD_RQUEST);
        }
    }
        @Test
    public void testParseHttpRequestToFailInvalidVersionRequest() {
        
        try {
            // TODO review the generated test code and remove the default call to fail.
            HttpRequest request = httpParser.parseHttpRequest(generateInValidVersionTestCase());
            
            //assertth(request.getMethod(), HttpMethod.GET );
            //fail("The test case is a prototype.");
            //the requset consists of 3 sections, request line, headers & body
        } catch (HttpParsingException ex) {
            assertEquals(ex.errorCode, HttpStatusCode.CLIENT_ERROR_400_BAD_RQUEST);
        }
    }    private InputStream generateValidGetTestCase(){
        
        String rawData = "GET / HTTP/1.1\r\n" +
        "Host: localhost:8080\r\n" +
        "Connection: keep-alive\r\n" +
        "Cache-Control: max-age=0\r\n" +
        "sec-ch-ua: \"Google Chrome\";v=\"131\", \"Chromium\";v=\"131\", \"Not_A Brand\";v=\"24\"\r\n" +
        "sec-ch-ua-mobile: ?0\r\n" +
        "sec-ch-ua-platform: \"Windows\"\r\n" +
        "Upgrade-Insecure-Requests: 1\r\n" +
        "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36\r\n" +
        "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7\r\n" +
        "Sec-Fetch-Site: none\r\n" +
        "Sec-Fetch-Mode: navigate\r\n" +
        "Sec-Fetch-User: ?1\r\n" +
        "Sec-Fetch-Dest: document\r\n" +
        "Accept-Encoding: gzip, deflate, br, zstd\r\n" +
        "Accept-Language: en-US,en;q=0.9,ar;q=0.8\r\n"+
                        "\r\n";
        InputStream inputStream = new ByteArrayInputStream(rawData.getBytes(StandardCharsets.US_ASCII));
        return inputStream;
    }
       private InputStream generateInValidGetTestCase(){
        
        String rawData = "Get / HTTP/1.1\r\n" +
        "Host: localhost:8080\r\n" +
        "Connection: keep-alive\r\n" +
        "Cache-Control: max-age=0\r\n" +
        "sec-ch-ua: \"Google Chrome\";v=\"131\", \"Chromium\";v=\"131\", \"Not_A Brand\";v=\"24\"\r\n" +
        "sec-ch-ua-mobile: ?0\r\n" +
        "sec-ch-ua-platform: \"Windows\"\r\n" +
        "Upgrade-Insecure-Requests: 1\r\n" +
        "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36\r\n" +
        "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7\r\n" +
        "Sec-Fetch-Site: none\r\n" +
        "Sec-Fetch-Mode: navigate\r\n" +
        "Sec-Fetch-User: ?1\r\n" +
        "Sec-Fetch-Dest: document\r\n" +
        "Accept-Encoding: gzip, deflate, br, zstd\r\n" +
        "Accept-Language: en-US,en;q=0.9,ar;q=0.8\r\n"+
                        "\r\n";
        InputStream inputStream = new ByteArrayInputStream(rawData.getBytes(StandardCharsets.US_ASCII));
        return inputStream;
    }
        private InputStream generateInValidTooLongGetTestCase(){
        
        String rawData = "Geeeeet / HTTP/1.1\r\n" +
        "Host: localhost:8080\r\n" +
        "Connection: keep-alive\r\n" +
        "Cache-Control: max-age=0\r\n" +
       
                        "\r\n";
        InputStream inputStream = new ByteArrayInputStream(rawData.getBytes(StandardCharsets.US_ASCII));
        return inputStream;
    }
    
        private InputStream generateInValidMoreElementsGetTestCase(){
        
        String rawData = "GET / AAAA HTTP/1.1\r\n" +
        "Host: localhost:8080\r\n" +
        "Connection: keep-alive\r\n" +
        "Cache-Control: max-age=0\r\n" +
       
                        "\r\n";
        InputStream inputStream = new ByteArrayInputStream(rawData.getBytes(StandardCharsets.US_ASCII));
        return inputStream;
    }
         private InputStream generateInValidEmptyGetTestCase(){
        
        String rawData = "\r\n" +
        "Host: localhost:8080\r\n" +
        "Connection: keep-alive\r\n" +
        "Cache-Control: max-age=0\r\n" +
       
                        "\r\n";
        InputStream inputStream = new ByteArrayInputStream(rawData.getBytes(StandardCharsets.US_ASCII));
        return inputStream;
    }
          private InputStream generateInValidVersionTestCase(){
        
        String rawData = "GET / Htp/1.1\r\n" +
        "Host: localhost:8080\r\n" +
        "Connection: keep-alive\r\n" +
        "Cache-Control: max-age=0\r\n" +
       
                        "\r\n";
        InputStream inputStream = new ByteArrayInputStream(rawData.getBytes(StandardCharsets.US_ASCII));
        return inputStream;
    }   
}
