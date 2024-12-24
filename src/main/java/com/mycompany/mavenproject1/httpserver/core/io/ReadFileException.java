/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1.httpserver.core.io;

/**
 *
 * @author kimoo
 */
public class ReadFileException extends Exception {

	public ReadFileException(String string) {
	super(string);
	}
	public ReadFileException(Exception e) {
	super(e);
	}
}
