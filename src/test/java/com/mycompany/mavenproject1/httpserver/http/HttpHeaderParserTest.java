/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1.httpserver.http;

/**
 *
 * @author kimoo
 */
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
public class HttpHeaderParserTest {
	private HttpParser httpParser;	
	private Method parserHeaderMethod;
	@BeforeAll
	public void before() throws NoSuchMethodException{
		httpParser = new HttpParser();
		Class<HttpParser> cl = HttpParser.class;
		parserHeaderMethod= cl.getDeclaredMethod("parseHeaders", InputStreamReader.class, HttpRequest.class );
		parserHeaderMethod.setAccessible(true);
	}
	@Test
	public void testPrivateMethod() throws IllegalAccessException, InvocationTargetException{
		HttpRequest request = new HttpRequest();
		
		parserHeaderMethod.invoke(httpParser, generateSimpleSingleHeaderMessage(), request);
		assertEquals(1, request.getHeadersNames().size());
		assertEquals("localhost:8080", request.getHeader("Host"));
	}	

	private InputStreamReader generateSimpleSingleHeaderMessage(){
        
        String rawData = 
        "Host: localhost:8080\r\n" ;
        InputStream inputStream = new ByteArrayInputStream(rawData.getBytes(StandardCharsets.US_ASCII));
	InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.US_ASCII);
        return reader;

    }
}
