/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1.httpserver.http;

import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author kimoo
 */
public class HttpRequest extends HttpMessage {

	HttpMethod method;
	String requestTarget;
	String httpVersion;
	HttpVersion bestVersion;
	private HashMap<String, String> headers = new HashMap<String,String>();
	public void addHeader(String fieldName, String fieldValue)
	{
		headers.put(fieldName, fieldValue);
	}
	public HashMap<String, String> getHeaders() {
		return headers;
	}
	public Set<String> getHeadersNames(){
		return headers.keySet();
	}
	public String getHeader(String headerName)
	{
		return headers.get(headerName);
	}
	public void setHeaders(HashMap<String, String> headers) {
		this.headers = headers;
	}

	public String getRequestTarget() {
		return requestTarget;
	}

	public void setMethod(String methodName) throws HttpParsingException {
		for (HttpMethod m : HttpMethod.values()) {

			if (methodName.equals(m.name())) {

				this.method = m;
				return;
			}
		}
		throw new HttpParsingException(HttpStatusCode.SERVER_ERROR_501_NOT_IMPLEMENTED);
	}

	public void setHttpVersion(String httpVersion) throws BadHttpVersionException, HttpParsingException {
		this.httpVersion = httpVersion;

		bestVersion = HttpVersion.getBestCompatibleVersion(httpVersion);
		if (bestVersion == null) {
			throw new HttpParsingException(HttpStatusCode.SERVER_ERROR_505_HTTP_VERSION_NOT_SUPPORTED);
		}
	}

	public HttpMethod getMethod() {
		return method;
	}

	void setRequestTarget(String requestTarget) throws HttpParsingException {
		if (requestTarget == null || requestTarget.length() == 0) {
			throw new HttpParsingException(HttpStatusCode.CLIENT_ERROR_400_BAD_RQUEST);
		}
		this.requestTarget = requestTarget;
	}
}
