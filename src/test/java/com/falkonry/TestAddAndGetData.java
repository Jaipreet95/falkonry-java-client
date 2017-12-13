package com.falkonry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/*!
 * falkonry-java-client
 * Copyright(c) 2017 Falkonry Inc
 * MIT Licensed
 */

import com.falkonry.client.Falkonry;
import com.falkonry.helper.models.Datasource;
import com.falkonry.helper.models.Datastream;
import com.falkonry.helper.models.EventType;
import com.falkonry.helper.models.Field;
import com.falkonry.helper.models.HttpResponseFormat;
import com.falkonry.helper.models.Input;
import com.falkonry.helper.models.InputStatus;
import com.falkonry.helper.models.Signal;
import com.falkonry.helper.models.TimeObject;
import com.falkonry.helper.models.ValueType;

//@Ignore
public class TestAddAndGetData {

	Falkonry falkonry = null;

//	String host = "https://localhost:8080";
//	String token = "auth-token";
	
	
	String host = "https://dev.falkonry.ai";
	String token = "n4qlyqyl7eejz9i2sc1bpi5bz6ry3wvx";
	
	List<Datastream> datastreams = new ArrayList<Datastream>();

	/**
	 *
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		falkonry = new Falkonry(host, token);
	}
	
	/**
	 * Add narrow input data (json format) to multi thing Datastream
	 * @throws Exception
	 */
	@Test
	public void addNarrowDataJson() throws Exception {

		Datastream ds = new Datastream();
		ds.setName("Test-DS-" + Math.random());
		TimeObject time = new TimeObject();
		time.setIdentifier("time");
		time.setFormat("iso_8601");
		time.setZone("GMT");
		Signal signal = new Signal();

		signal.setValueIdentifier("value");
		signal.setSignalIdentifier("signal");
		Field field = new Field();
		field.setEntityIdentifier("unit");
		
		field.setSignal(signal);
		field.setTime(time);
		ds.setField(field);
		Datasource dataSource = new Datasource();
		dataSource.setType("STANDALONE");
		ds.setDatasource(dataSource);

		Datastream datastream = falkonry.createDatastream(ds);
		datastreams.add(datastream);
		String data = "{\"time\" :\"2016-03-01T01:01:01.000Z\",\"unit\":\"Unit1\", \"signal\" : \"current\", \"value\" : 12.5}" +
					"{\"time\" :\"2016-03-01T01:01:01.000Z\",\"unit\":\"Unit2\", \"signal\" : \"vibration\", \"value\" : 3.4}";

		Map<String, String> options = new HashMap<String, String>();
		options.put("timeIdentifier", "time");
		options.put("timeFormat", "iso_8601");
		options.put("timeZone", time.getZone());
		options.put("signalIdentifier", "signal");
		options.put("entityIdentifier", "unit");
		options.put("valueIdentifier", "value");
		options.put("fileFormat", "json");
		options.put("streaming", "false");
		options.put("hasMoreData", "false");

		InputStatus ins = falkonry.addInput(datastream.getId(), data, options);
		Assert.assertEquals(ins.getAction(), "ADD_DATA_DATASTREAM");
		Assert.assertEquals(ins.getStatus(), "PENDING");
		
		// Get Datastream Data
		options = new HashMap<String, String>();
	    options.put("responseFormat", "application/json");  // also available options 1. text/csv 2. application/json
		HttpResponseFormat dataResponse = falkonry.getInputData(datastream.getId(), options);
		//Assert.assertEquals(dataResponse.getResponse().length()>0,true);
	}
	
	/**
	 * Add narrow input data (csv format) single thing to Datastream
	 * @throws Exception
	 */
	@Test
	public void addNarrowDataCsv() throws Exception {

		Datastream ds = new Datastream();
		ds.setName("Test-DS-" + Math.random());
		TimeObject time = new TimeObject();
		time.setIdentifier("time");
		time.setFormat("iso_8601");
		time.setZone("GMT");
		Signal signal = new Signal();

		signal.setValueIdentifier("value");
		signal.setSignalIdentifier("signal");

		Field field = new Field();
		field.setSignal(signal);
		field.setTime(time);
		ds.setField(field);
		Datasource dataSource = new Datasource();
		dataSource.setType("STANDALONE");
		ds.setDatasource(dataSource);

		Datastream datastream = falkonry.createDatastream(ds);
		datastreams.add(datastream);
		String data = "time,signal,value\n" + 
						"2012-01-03T18:16:00.000Z,L1DynVert,9.95\n" + 
						"2012-01-03T18:16:00.000Z,L1VertAvg,12.95\n" + 
						"2012-01-03T18:16:00.000Z,L1VertPk,19.95";

		Map<String, String> options = new HashMap<String, String>();
//		options.put("timeIdentifier", "time");
//		options.put("timeFormat", "iso_8601");
//		options.put("timeZone", time.getZone());
//		options.put("signalIdentifier", "signal");
//		options.put("valueIdentifier", "value");
		options.put("fileFormat", "csv");
		options.put("streaming", "false");
		options.put("hasMoreData", "false");

		InputStatus ins = falkonry.addInput(datastream.getId(), data, options);
		Assert.assertEquals(ins.getAction(), "ADD_DATA_DATASTREAM");
		Assert.assertEquals(ins.getStatus(), "PENDING");
		
		// Get Datastream Data
		options = new HashMap<String, String>();
	    options.put("responseFormat", "application/json");  // also available options 1. text/csv 2. application/json
		HttpResponseFormat dataResponse = falkonry.getInputData(datastream.getId(), options);
		Assert.assertEquals(dataResponse.getResponse().length()>0,true);
	}
	
	

	/**
	 * Add wide input data (json format) to single thing Datastream
	 * @throws Exception
	 */
	@Test
	public void addDataJson() throws Exception {
		Datastream ds = new Datastream();
		ds.setName("Test-DS-" + Math.random());
		TimeObject time = new TimeObject();
		time.setIdentifier("time");
		time.setFormat("YYYY-MM-DD HH:mm:ss");
		time.setZone("GMT");

		Field field = new Field();
		field.setTime(time);
		ds.setField(field);
		Datasource dataSource = new Datasource();
		dataSource.setType("PI");
		dataSource.sethost("https://test.piserver.com/piwebapi");
		dataSource.setElementTemplateName("SampleElementTempalte");
		ds.setDatasource(dataSource);

		// Input List
		List<Input> inputList = new ArrayList<Input>();
		Input currents = new Input();
		ValueType valueType = new ValueType();
		EventType eventType = new EventType();
		currents.setName("current");
		valueType.setType("Numeric");
		eventType.setType("Samples");
		currents.setValueType(valueType);
		currents.setEventType(eventType);
		inputList.add(currents);

		Input vibration = new Input();
		vibration.setName("vibration");
		valueType.setType("Numeric");
		eventType.setType("Samples");
		vibration.setValueType(valueType);
		vibration.setEventType(eventType);
		inputList.add(vibration);

		Input state = new Input();
		state.setName("state");
		valueType.setType("Categorical");
		eventType.setType("Samples");
		state.setValueType(valueType);
		state.setEventType(eventType);
		inputList.add(state);

		ds.setInputList(inputList);

		Datastream datastream = falkonry.createDatastream(ds);
		datastreams.add(datastream);
		String data = "{\"time\" :\"2016-03-01 01:01:01\", \"current\" : 12.4, \"vibration\" : 3.4, \"state\" : \"On\"}";

		Map<String, String> options = new HashMap<String, String>();
//		options.put("timeIdentifier", "time");
//		options.put("timeFormat", "YYYY-MM-DD HH:mm:ss");
		options.put("fileFormat", "json");
		options.put("streaming", "false");
		options.put("hasMoreData", "false");

		InputStatus ins = falkonry.addInput(datastream.getId(), data, options);
		Assert.assertEquals(ins.getAction(), "ADD_DATA_DATASTREAM");
		Assert.assertEquals(ins.getStatus(), "PENDING");
		
		// Get Datastream Data
		options = new HashMap<String, String>();
	    options.put("responseFormat", "text/csv");  // also available options 1. text/csv 2. application/json
		HttpResponseFormat dataResponse = falkonry.getInputData(datastream.getId(), options);
		Assert.assertEquals(dataResponse.getResponse().length()>0,true);
	}

	
	/**
	 * Add wide input data (json format) to single thing Datastream
	 * @throws Exception
	 */
	@Test
	public void addDataCsv() throws Exception {

		Datastream ds = new Datastream();
		ds.setName("Test-DS-" + Math.random());
		TimeObject time = new TimeObject();
		time.setIdentifier("time");
		time.setFormat("iso_8601");
		time.setZone("GMT");
		Signal signal = new Signal();
		
		signal.setValueIdentifier("value");
		signal.setSignalIdentifier("signal");
		

		Field field = new Field();
		field.setSignal(signal);
		field.setTime(time);
		ds.setField(field);
		Datasource dataSource = new Datasource();
		dataSource.setType("STANDALONE");
		ds.setDatasource(dataSource);

		Datastream datastream = falkonry.createDatastream(ds);
		datastreams.add(datastream);
		String data = "time,unit,L1DynVert,L1VertAvg,L1VertPk\n" + 
        "2012-01-03T18:16:00.000Z,unit1,4.6,9.95,89.95\n" + 
        "2012-01-03T18:16:00.000Z,unit1,5.2,12.95,5.85\n" + 
        "2012-01-03T18:16:00.000Z,unit2,74.3,19.95,9.0";

		Map<String, String> options = new HashMap<String, String>();
		options.put("timeIdentifier", "time");
		options.put("timeFormat", "iso_8601");
		options.put("timeZone", time.getZone());
		options.put("fileFormat", "csv");
		options.put("streaming", "false");
		options.put("hasMoreData", "false");
		options.put("signalIdentifier", "time");
		options.put("entityIdentifier", "time");
		options.put("valueIdentifier", "unit");

		InputStatus ins = falkonry.addInput(datastream.getId(), data, options);
		Assert.assertEquals(ins.getAction(), "ADD_DATA_DATASTREAM");
		Assert.assertEquals(ins.getStatus(), "PENDING");
		
		// Get Datastream Data
		options = new HashMap<String, String>();
	    options.put("responseFormat", "application/json");  // also available options 1. text/csv 2. application/json
		HttpResponseFormat dataResponse = falkonry.getInputData(datastream.getId(), options);
		Assert.assertEquals(dataResponse.getResponse().length()>0,true);
	}

	@After
	public void cleanUp() throws Exception {
		Iterator<Datastream> itr = datastreams.iterator();
		while (itr.hasNext()) {
			Datastream ds = itr.next();
			falkonry.deleteDatastream(ds.getId());
		}
	}
}
