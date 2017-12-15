package com.falkonry;

/*!
 * falkonry-java-client
 * Copyright(c) 2017 Falkonry Inc
 * MIT Licensed
 */

import com.falkonry.client.Falkonry;
import com.falkonry.helper.models.Assessment;
import com.falkonry.helper.models.AssessmentRequest;
import com.falkonry.helper.models.Datasource;
import com.falkonry.helper.models.Datastream;
import com.falkonry.helper.models.Field;
import com.falkonry.helper.models.TimeObject;
import com.falkonry.helper.models.Signal;
import org.junit.*;
import java.util.*;

@Ignore
public class TestCreateAssessment {

	Falkonry falkonry = null;
	String host = "https://localhost:8080";
	String token = "auth-token";

	List<Datastream> datastreams = new ArrayList<Datastream>();
	List<Assessment> assessments = new ArrayList<Assessment>();

	/**
	 *
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		falkonry = new Falkonry(host, token);
	}

	/**
	 * Should create assessment
	 * 
	 * @throws Exception
	 */
	@Test
	public void createAssessment() throws Exception {
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
		// field.setEntityIdentifier("entity");

		field.setSignal(signal);
		field.setTime(time);
		ds.setField(field);
		Datasource dataSource = new Datasource();
		dataSource.setType("STANDALONE");
		ds.setDatasource(dataSource);
		Datastream datastream = falkonry.createDatastream(ds);
		datastreams.add(datastream);

		AssessmentRequest assessmentRequest = new AssessmentRequest();
		String name = "Test-AS-" + Math.random();
		assessmentRequest.setName(name);
		assessmentRequest.setDatastream(datastream.getId());
		assessmentRequest.setAssessmentRate("PT1S");
		Assessment assessment = falkonry.createAssessment(assessmentRequest);
		assessments.add(assessment);
		Assessment assessmentResp = falkonry.getAssessment(assessment.getId());
		Assert.assertEquals(assessmentResp.getName(), assessmentRequest.getName());
		Assert.assertEquals(assessmentResp.getRate(), assessmentRequest.getAssessmentRate());
		falkonry.deleteAssessment(assessment.getId());

	}

	/**
	 * Should delete assessment
	 * 
	 * @throws Exception
	 */
	@Test
	public void deleteAssessment() throws Exception {
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
		// field.setEntityIdentifier("entity");

		field.setSignal(signal);
		field.setTime(time);
		ds.setField(field);
		Datasource dataSource = new Datasource();
		dataSource.setType("STANDALONE");
		ds.setDatasource(dataSource);

		Datastream datastream = falkonry.createDatastream(ds);
		datastreams.add(datastream);

		AssessmentRequest assessmentRequest = new AssessmentRequest();
		String name = "Test-AS-" + Math.random();
		assessmentRequest.setName(name);
		assessmentRequest.setDatastream(datastream.getId());
		assessmentRequest.setAssessmentRate("PT1S");
		Assessment assessment = falkonry.createAssessment(assessmentRequest);
		assessments.add(assessment);

		Assert.assertEquals(assessment.getName(), assessmentRequest.getName());
		Assert.assertEquals(assessment.getRate(), assessmentRequest.getAssessmentRate());
		falkonry.deleteAssessment(assessment.getId());
		try {
			Assessment assessmentResp = falkonry.getAssessment(assessment.getId());
			Assert.assertEquals(true, false);
		} catch (Exception e) {
			Assert.assertEquals(true, true);
		}
	}

	/**
	 * Should get assessment list
	 * 
	 * @throws Exception
	 */
	@Test
	public void getAssessmentList() throws Exception {

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
		// field.setEntityIdentifier("entity");

		field.setSignal(signal);
		field.setTime(time);
		ds.setField(field);
		Datasource dataSource = new Datasource();
		dataSource.setType("STANDALONE");
		ds.setDatasource(dataSource);

		Datastream datastream = falkonry.createDatastream(ds);
		datastreams.add(datastream);

		AssessmentRequest assessmentRequest = new AssessmentRequest();
		String name = "Test-AS-" + Math.random();
		assessmentRequest.setName(name);
		assessmentRequest.setDatastream(datastream.getId());
		assessmentRequest.setAssessmentRate("PT1S");
		Assessment assessment = falkonry.createAssessment(assessmentRequest);
		assessments.add(assessment);

		Assert.assertEquals(assessment.getName(), assessmentRequest.getName());
		Assert.assertEquals(assessment.getRate(), assessmentRequest.getAssessmentRate());

		List<Assessment> assessmentList = falkonry.getAssessments();
		Assert.assertEquals(true, assessmentList.size() > 2);

		falkonry.deleteAssessment(assessment.getId());
	}

	/**
	 * Should get assessment by ID
	 * 
	 * @throws Exception
	 */
	@Test
	public void getAssessmentById() throws Exception {

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
		// field.setEntityIdentifier("entity");

		field.setSignal(signal);
		field.setTime(time);
		ds.setField(field);
		Datasource dataSource = new Datasource();
		dataSource.setType("STANDALONE");
		ds.setDatasource(dataSource);

		Datastream datastream = falkonry.createDatastream(ds);
		datastreams.add(datastream);

		AssessmentRequest assessmentRequest = new AssessmentRequest();
		String name = "Test-AS-" + Math.random();
		assessmentRequest.setName(name);
		assessmentRequest.setDatastream(datastream.getId());
		assessmentRequest.setAssessmentRate("PT1S");
		Assessment assessment = falkonry.createAssessment(assessmentRequest);
		assessments.add(assessment);

		Assert.assertEquals(assessment.getName(), assessmentRequest.getName());
		Assert.assertEquals(assessment.getRate(), assessmentRequest.getAssessmentRate());

		Assessment assessment1 = falkonry.getAssessment(assessment.getId());
		Assert.assertEquals(assessment1.getName(), assessment.getName());
		Assert.assertEquals(assessment1.getRate(), assessment.getRate());
		Assert.assertEquals(assessment1.getDatastream(), assessment.getDatastream());

		// aprioriConditionList
		List<String> aprioriConditionList = assessment1.getAprioriConditionList();
		Assert.assertEquals(aprioriConditionList.size(), 0);

		falkonry.deleteAssessment(assessment.getId());
	}

	// @Test

	/**
	 * Should update assessment
	 * 
	 * @throws Exception
	 */
	public void updateAssessment() throws Exception {

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
		// field.setEntityIdentifier("entity");

		field.setSignal(signal);
		field.setTime(time);
		ds.setField(field);
		Datasource dataSource = new Datasource();
		dataSource.setType("STANDALONE");
		ds.setDatasource(dataSource);
		Datastream datastream = falkonry.createDatastream(ds);
		datastreams.add(datastream);

		AssessmentRequest assessmentRequest = new AssessmentRequest();
		String name = "Test-AS-" + Math.random();
		assessmentRequest.setName(name);
		assessmentRequest.setDatastream(datastream.getId());
		assessmentRequest.setAssessmentRate("PT1S");
		Assessment assessment = falkonry.createAssessment(assessmentRequest);
		assessments.add(assessment);

		Assert.assertEquals(assessment.getName(), assessmentRequest.getName());
		Assert.assertEquals(assessment.getRate(), assessmentRequest.getAssessmentRate());

		Assessment assessment1 = falkonry.getAssessment(assessment.getId());
		Assert.assertEquals(assessment1.getName(), assessment.getName());
		Assert.assertEquals(assessment1.getRate(), assessment.getRate());
		Assert.assertEquals(assessment1.getDatastream(), assessment.getDatastream());

		name = "Test-AS-" + Math.random();
		assessment1.setName(name);
		Assessment assessmentUpd = falkonry.updateAssessment(assessment1);
		Assert.assertEquals(assessmentUpd.getName(), assessment1.getName());
		Assert.assertEquals(assessmentUpd.getRate(), assessment1.getRate());
		Assert.assertEquals(assessmentUpd.getDatastream(), assessment1.getDatastream());

		falkonry.deleteAssessment(assessment.getId());
	}

	/**
	 * Should create Narrow datastream with CSV data
	 * 
	 * @throws Exception
	 */
	@Test
	public void createDatastreamWithCsvData() throws Exception {

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
		field.setEntityIdentifier("entity");

		field.setSignal(signal);
		field.setTime(time);
		ds.setField(field);
		Datasource dataSource = new Datasource();
		dataSource.setType("STANDALONE");
		ds.setDatasource(dataSource);
		Datastream datastream = falkonry.createDatastream(ds);
		datastreams.add(datastream);

		String data = "time,signal,entity,value\n2016-03-01 01:01:01,signal1,entity1,3.4";

		Map<String, String> options = new HashMap<String, String>();
		options.put("timeIdentifier", "time");
		options.put("timeFormat", "YYYY-MM-DD HH:mm:ss");
		options.put("timeZone", time.getZone());
		options.put("signalIdentifier", "signal");
		options.put("entityIdentifier", "entity");
		options.put("valueIdentifier", "value");
		options.put("fileFormat", "csv");
		options.put("streaming", "false");
		options.put("hasMoreData", "false");
		falkonry.addInput(datastream.getId(), data, options);

		Assert.assertEquals(ds.getName(), datastream.getName());
		Assert.assertNotEquals(null, datastream.getId());

		Assert.assertEquals(datastream.getField().getTime().getFormat(), ds.getField().getTime().getFormat());
		Assert.assertEquals(datastream.getField().getTime().getIdentifier(), ds.getField().getTime().getIdentifier());
		Assert.assertEquals(datastream.getField().getTime().getZone(), ds.getField().getTime().getZone());

		Assert.assertEquals(datastream.getDatasource().getType(), ds.getDatasource().getType());

		Assert.assertEquals(datastream.getField().getSignal().getValueIdentifier(),
				ds.getField().getSignal().getValueIdentifier());

	}

	/**
	 * Should create wide datastream with CSV data
	 * 
	 * @throws Exception
	 */
	@Test
	public void createDatastreamWithWideCsvData() throws Exception {

		Datastream ds = new Datastream();
		ds.setName("Test-DS-" + Math.random());
		TimeObject time = new TimeObject();
		time.setIdentifier("time");
		time.setFormat("millis");
		time.setZone("GMT");
		Signal signal = new Signal();

		signal.setValueIdentifier("value");
		signal.setSignalIdentifier("signal");
		Field field = new Field();
		// field.setEntityIdentifier("entity");

		field.setSignal(signal);
		field.setTime(time);
		ds.setField(field);
		Datasource dataSource = new Datasource();
		dataSource.setType("STANDALONE");
		ds.setDatasource(dataSource);

		Datastream datastream = falkonry.createDatastream(ds);
		datastreams.add(datastream);

		String data = "time,entity,signal1,signal2,signal3,signal4,signal5\n"
				+ "1467729675422,entity1,41.11,82.34,74.63,4.8,72.01";
		Map<String, String> options = new HashMap<String, String>();
		options.put("fileFormat", "csv");
		options.put("streaming", "false");
		options.put("hasMoreData", "false");
		falkonry.addInput(datastream.getId(), data, options);

		Assert.assertEquals(ds.getName(), datastream.getName());
		Assert.assertNotEquals(null, datastream.getId());

		Assert.assertEquals(datastream.getField().getTime().getFormat(), ds.getField().getTime().getFormat());
		Assert.assertEquals(datastream.getField().getTime().getIdentifier(), ds.getField().getTime().getIdentifier());
		Assert.assertEquals(datastream.getField().getTime().getZone(), ds.getField().getTime().getZone());

		Assert.assertEquals(datastream.getDatasource().getType(), ds.getDatasource().getType());
		Assert.assertEquals(datastream.getField().getSignal().getValueIdentifier(),
				ds.getField().getSignal().getValueIdentifier());

	}

	/**
	 *
	 * @throws Exception
	 */
	@After
	public void cleanUp() throws Exception {
		Iterator<Datastream> itr = datastreams.iterator();
		while (itr.hasNext()) {
			Datastream ds = itr.next();
			falkonry.deleteDatastream(ds.getId());
		}
	}
}
