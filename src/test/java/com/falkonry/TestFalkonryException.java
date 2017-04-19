package com.falkonry;

/*!
 * falkonry-java-client
 * Copyright(c) 2016 Falkonry Inc
 * MIT Licensed
 */

import com.falkonry.client.Falkonry;
import com.falkonry.helper.models.Assessment;
import com.falkonry.helper.models.Datastream;

import org.junit.*;

import java.util.*;

/**
 *
 */
public class TestFalkonryException {

	Falkonry falkonry = null;
	String host = "https://localhost:8080";
	String token = "8g462njx92e1yc0fxzrbdxqtx90hsr1s";

	/**
	 *
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		falkonry = new Falkonry(host, token);
	}

	/**
	 *
	 * @throws Exception
	 */
	@Test
	public void exceptionFetchingDatastream() throws Exception {
		try {
			Datastream ds = falkonry.getDatastream("123");
		} catch (Exception e) {
			String msg = e.getMessage();
			Assert.assertEquals(msg, "{\"message\":\"No such Datastream available\"}");
		}
	}

	/**
	 *
	 * @throws Exception
	 */
	@Test
	public void exceptionFetchingAssessment() throws Exception {
		try {
			Assessment asmt = falkonry.getAssessment("123");
		} catch (Exception e) {
			String msg = e.getMessage();
			Assert.assertEquals(msg, "{\"message\":\"No such assessment available\"}");
		}
	}

}
