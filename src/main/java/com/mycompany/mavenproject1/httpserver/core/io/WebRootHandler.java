/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1.httpserver.core.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kimoo
 */
public class WebRootHandler {
	
	private File webRoot;
	
	public WebRootHandler(String webRootPath) throws WebRootNotFoundException
	{
		webRoot = new File(webRootPath);
		if(!webRoot.exists() || !webRoot.isDirectory() )
		{
			throw new WebRootNotFoundException("WebRoot provided does not exist or is not foler");
		}
	}
	public boolean doesEndWithSlash(String relativePath)
	{
		return relativePath.endsWith("/");
	}
	public boolean doesRelativePathExists(String relativePath) 
	{
		File file = new File(webRoot, relativePath);
		if(!file.exists())
			return false;
		try {
			if(file.getCanonicalPath().startsWith(webRoot.getCanonicalPath()))
			{	
				return true;
			}
		} catch (IOException ex) {
		return false;
		}
		return false;
	}
	public String getFileMimeType(String relativePath) throws FileNotFoundException
	{
		if(doesEndWithSlash(relativePath))
			relativePath += "index.html";
		if(!doesRelativePathExists(relativePath))
			throw new FileNotFoundException("File not found: "+ relativePath);
		File file = new File(webRoot, relativePath);
		String mimeType = URLConnection.getFileNameMap().getContentTypeFor(file.getName());
		if(mimeType == null)
			return "application/octet-stream";
		return mimeType;	
	}
	public byte[] getFileData(String relativePath) throws FileNotFoundException, ReadFileException
	{
		if(doesEndWithSlash(relativePath))
			relativePath += "index.html";
		if(!doesRelativePathExists(relativePath))
			throw new FileNotFoundException("File not found: "+ relativePath);
		File file = new File(webRoot, relativePath);
	
		FileInputStream inputStream = new FileInputStream(file);
		byte[] bytes = new byte[(int)file.length()];
		try{
			inputStream.read(bytes);
			inputStream.close();
		}
		catch(IOException ex)
		{
			throw new ReadFileException(ex);
		}
		return bytes;
	}
}
