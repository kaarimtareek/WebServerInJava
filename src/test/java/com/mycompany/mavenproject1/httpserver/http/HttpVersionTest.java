/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1.httpserver.http;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.TestInstance;

/**
 *
 * @author kimoo
 */

public class HttpVersionTest {

	@Test
	void getBestCompatibleVersionExactMatch() {
		HttpVersion version = null;
		try {
			version = HttpVersion.getBestCompatibleVersion("HTTP/1.1");
		} catch (BadHttpVersionException ex) {
			fail(ex);
		}
		assertNotNull(version);
		assertEquals(version, HttpVersion.HTTP_1_1);
	}

	@Test
	void getBestCompatibleVersionBadFormat() {
		HttpVersion version = null;
		try {
			version = HttpVersion.getBestCompatibleVersion("http/1.1");
			fail();
		} catch (BadHttpVersionException ex) {
		}
	}

	@Test
	void getBestCompatibleVersionHigherMinorVersion() {

		HttpVersion version = null;
		try {
			version = HttpVersion.getBestCompatibleVersion("HTTP/1.2");
			assertNotNull(version);
			assertEquals(version, HttpVersion.HTTP_1_1);
		} catch (BadHttpVersionException ex) {
			fail();
		}
	}
}
