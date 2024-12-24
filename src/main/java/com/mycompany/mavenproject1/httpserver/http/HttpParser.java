/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1.httpserver.http;

import com.mycompany.mavenproject1.httpserver.http.HttpParsingException;
import com.mycompany.mavenproject1.httpserver.http.HttpStatusCode;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author kimoo
 */
public class HttpParser {

	private final static Logger LOGGER = LoggerFactory.getLogger(HttpParser.class);

	private static final int SP = 0x20;
	private static final int CR = 0x0D;
	private static final int LF = 0x0A;

	public HttpRequest parseHttpRequest(InputStream inputStream) throws HttpParsingException {
		InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.US_ASCII);
		HttpRequest request = new HttpRequest();
		try {
			//the requset consists of 3 sections, request line, headers & body
			parseRequestLine(reader, request);
		} catch (IOException ex) {
			LOGGER.error("Error processing request line");
		}
		try {
			//the requset consists of 3 sections, request line, headers & body
			parseHeaders(reader, request);
		} catch (IOException ex) {
			LOGGER.error("Error processing request line");
		}
//        parseRequestHeaders(reader, request);
		//       parseRequestBody(reader, request);

		return request;

	}

	private void parseRequestLine(InputStreamReader reader, HttpRequest request) throws IOException, HttpParsingException {
		int _byte;
		StringBuilder buffer = new StringBuilder();
		boolean methodParsed = false;
		boolean requestTargetParsed = false;
		while ((_byte = reader.read()) >= 0) {
			if (_byte == CR) {
				_byte = reader.read();
				if (_byte == LF) {
					LOGGER.debug("Request Line Version to process: {}", buffer.toString());
					if (!methodParsed || !requestTargetParsed) {
						throw new HttpParsingException(HttpStatusCode.CLIENT_ERROR_400_BAD_RQUEST);
					}

					try {
						request.setHttpVersion(buffer.toString());
					} catch (BadHttpVersionException ex) {
						throw new HttpParsingException(HttpStatusCode.CLIENT_ERROR_400_BAD_RQUEST);
					}
					return;
				} else {
					throw new HttpParsingException(HttpStatusCode.CLIENT_ERROR_400_BAD_RQUEST);
				}
			}
			if (_byte == SP) {
				if (!methodParsed) {
					String methodName = buffer.toString();
					LOGGER.debug("Request Line metod to process: {}", methodName);

					request.setMethod(methodName);
					methodParsed = true;
				} else if (!requestTargetParsed) {
					String requestTarget = buffer.toString();
					request.setRequestTarget(requestTarget);
					LOGGER.debug("Request Line Target to process: {}", buffer.toString());
					requestTargetParsed = true;
				} else {
					throw new HttpParsingException(HttpStatusCode.CLIENT_ERROR_400_BAD_RQUEST);
				}
				buffer.delete(0, buffer.length());
			} else {
				buffer.append((char) _byte);
				if (!methodParsed) {
					if (buffer.length() > HttpMethod.MAX_LENGTH) {
						throw new HttpParsingException(HttpStatusCode.CLIENT_ERROR_401_METHOD_NOT_ALLOWED);
					}
				}
			}
		}
	}

	public void parseHeaders(InputStreamReader reader, HttpRequest request)
		throws HttpParsingException, HttpParsingException, IOException {
		int _byte;
		StringBuilder buffer = new StringBuilder();
		boolean crlfFound = false;
		while ((_byte = reader.read()) >= 0) {
			if (_byte == CR) {
				_byte = reader.read();
				if (_byte == LF) {
					if (!crlfFound) {
						crlfFound = true;
						processSingleHeaderLine(buffer, request);

						buffer.delete(0, buffer.length());
					} else {
						return;
					}
				} else {

					throw new HttpParsingException(HttpStatusCode.CLIENT_ERROR_400_BAD_RQUEST);
				}
			} else {
				crlfFound = false;
				//
				buffer.append((char)_byte);
			}
		}
	}
private void processSingleHeaderLine(StringBuilder sb, HttpRequest request) throws HttpParsingException
{
	String rawField = sb.toString();
        Pattern pattern = 
		Pattern.compile("^(?<fieldName>[!#$%&’*+\\-./^_‘|˜\\dA-Za-z]+):\\s?(?<fieldValue>[!#$%&’*+\\-./^_‘|˜(),:;<=>?@[\\\\]{}\" \\dA-Za-z]+)\\s?$");
	Matcher matcher = pattern.matcher(rawField);
	if(matcher.matches())
	{
		String fieldName = matcher.group("fieldName");
		String fieldValue = matcher.group("fieldValue");
		request.addHeader(fieldName, fieldValue);

	}
	else{
		throw new HttpParsingException(HttpStatusCode.CLIENT_ERROR_400_BAD_RQUEST);
	}
}
	private void parseRequestBody(InputStreamReader reader, HttpRequest request) {
		throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
	}

}
