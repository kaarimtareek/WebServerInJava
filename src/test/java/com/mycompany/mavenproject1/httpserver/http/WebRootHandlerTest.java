/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.mavenproject1.httpserver.http;

import com.mycompany.mavenproject1.httpserver.core.io.WebRootHandler;
import com.mycompany.mavenproject1.httpserver.core.io.WebRootNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

/**
 *
 * @author kimoo
 */
@TestInstance(Lifecycle.PER_CLASS)
public class WebRootHandlerTest {
	
	public WebRootHandlerTest() {
	}

	@Test
	public void constructorGoodPath(){
		
		
		try {
			WebRootHandler  webRootHandler = new WebRootHandler("E:\\WebServer\\mavenproject1\\WebRoot");
		} catch (WebRootNotFoundException ex) {
			fail(ex);
		} 
	}
	@Test
	public void constructorBadPath(){
		
		
		try {
			WebRootHandler  webRootHandler = new WebRootHandler("E:\\WebServer\\mavenpr\\WebRoot");
			fail();
		} catch (WebRootNotFoundException ex) {
		} 
	}
	@Test
	public void constructorGoodRelativePath(){
		
		
		try {
			WebRootHandler  webRootHandler = new WebRootHandler("WebRoot");
		} catch (WebRootNotFoundException ex) {
			fail();
		} 
	}
	// TODO add test methods here.
	// The methods must be annotated with annotation @Test. For example:
	//
	// @Test
	// public void hello() {}
}
