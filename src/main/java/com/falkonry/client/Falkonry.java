package com.falkonry.client;

/*!
 * falkonry-java-client
 * Copyright(c) 2016 Falkonry Inc
 * MIT Licensed
 */

import com.falkonry.client.service.FalkonryService;
import com.falkonry.helper.models.*;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Map;
import java.util.Observer;


public class Falkonry {

  private FalkonryService falkonryService;

  public Falkonry(String host, String token) throws Exception {
    this.falkonryService = new FalkonryService(host, token);
  }

  public Datastream createDatastream(Datastream eventbuffer) throws Exception {
    return falkonryService.createDatastream(eventbuffer);
}

  public List<Datastream> getDatastreams() throws Exception {
    return falkonryService.getDatastreams();
  }

  public Datastream getUpdatedDatastream(String id) throws Exception{
    return falkonryService.getUpdatedDatastream(id);
  }

  public void deleteDatastream(String eventbuffer) throws Exception {
    falkonryService.deleteDatastream(eventbuffer);
  }

  public Assessment createAssessment(Assessment assessment) throws Exception {
    return falkonryService.createAssessment(assessment);
  }

  public List<Assessment> getAssessment() throws Exception {
    return falkonryService.getAssessments();
  }

  public void deleteAssessment(String assessment) throws Exception {
    this.falkonryService.deleteAssessment(assessment);
  }

  public InputStatus addInput(String eventbuffer, String data, Map<String, String> options) throws Exception {
    return this.falkonryService.addInputData(eventbuffer, data, options);
  }

  public String addFacts(String assessment,  String data, Map<String, String> options) throws Exception{
    return this.falkonryService.addFacts(assessment, data, options);
  }

  public InputStatus addInputStream(String eventbuffer, ByteArrayInputStream stream, Map<String, String> options) throws Exception {
    return this.falkonryService.addInputFromStream(eventbuffer, stream, options);
  }

  public String addFactsStream(String assessment, ByteArrayInputStream stream, Map<String, String> options) throws Exception{
    return this.falkonryService.addFactsStream(assessment,stream,options);
  }

  public BufferedReader getOutput(String assessment, Long start, Long end) throws Exception {
    return this.falkonryService.getOutput(assessment, start, end);
  }

  // public Observer streamOutput(String assessment, Long start) throws Exception {
  //   return this.falkonryService.streamOutput(assessment, start);
  // }
}
