/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1.httpserver.core.io;

/**
 *
 * @author kimoo
 */
public class WebRootNotFoundException extends Exception {

	public WebRootNotFoundException(String webRoot_provided_does_not_exist_or_is_not) {
		super(webRoot_provided_does_not_exist_or_is_not);
	}
	
}
