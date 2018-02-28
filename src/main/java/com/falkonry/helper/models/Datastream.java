package com.falkonry.helper.models;

/*!
 * falkonry-java-client
 * Copyright(c) 2017 Falkonry Inc
 * MIT Licensed
 */

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

/**
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class Datastream {
  private String id;
  private String sourceId;
  private String name;
  private String tenant;
  private String createdBy;
  private Long createTime;
  private String updatedBy;
  private Long updateTime;
  private Stats stats;
  private Field field;
  private Datasource dataSource;
  private List<Input> inputList;
  private String timePrecision;
  private String live;
    /**
     *
     * @return
     */
    public String getId() {
    return id;
  }

    /**
     *
     * @param id
     * @return
     */
    public Datastream setId(String id) {
    this.id = id;
    return this;
  }

    /**
     *
     * @return
     */
    public String getSourceId() {
    return sourceId;
  }

    /**
     *
     * @param sourceId
     * @return
     */
    public Datastream setSourceId(String sourceId) {
    this.sourceId = sourceId;
    return this;
  }

    /**
     *
     * @return
     */
    public String getName() {
    return name;
  }

    /**
     *
     * @param name
     * @return
     */
    public Datastream setName(String name) {
    this.name = name;
    return this;
  }

    /**
     *
     * @return
     */
    @JsonProperty("tenant")
  public String getAccount() {
    return tenant;
  }

    /**
     *
     * @param account
     * @return
     */
    @JsonProperty("tenant")
  public Datastream setAccount(String account) {
    this.tenant = account;
    return this;
  }

    /**
     *
     * @return
     */
    public String getCreatedBy() {
    return createdBy;
  }

    /**
     *
     * @param createdBy
     * @return
     */
    public Datastream setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
    return this;
  }

    /**
     *
     * @return
     */
    public Long getCreateTime() {
    return createTime;
  }

    /**
     *
     * @param createTime
     * @return
     */
    public Datastream setCreateTime(Long createTime) {
    this.createTime = createTime;
    return this;
  }

    /**
     *
     * @return
     */
    public String getUpdatedBy() {
    return updatedBy;
  }

    /**
     *
     * @param updatedBy
     * @return
     */
    public Datastream setUpdatedBy(String updatedBy) {
    this.updatedBy = updatedBy;
    return this;
  }

    /**
     *
     * @return
     */
    public Long getUpdateTime() {
    return updateTime;
  }

    /**
     *
     * @param updateTime
     * @return
     */
    public Datastream setUpdateTime(Long updateTime) {
    this.updateTime = updateTime;
    return this;
  }

    /**
     *
     * @return
     */
    public Stats getStats() {
    return stats;
  }

    /**
     *
     * @param stats
     * @return
     */
    public Datastream setStats(Stats stats)
  {
    this.stats = stats;
    return this;
  }

    /**
     *
     * @return
     */
    public Field getField() {
    return field;
  }

    /**
     *
     * @param field
     * @return
     */
    public Datastream setField(Field field) {
    this.field = field;
    return this;
  }
  
    /**
     *
     * @return
     */
    @JsonProperty("dataSource")
  public Datasource getDatasource() {
    return dataSource;
  }

    /**
     *
     * @param dataSource
     * @return
     */
    @JsonProperty("dataSource")
  public Datastream setDatasource(Datasource dataSource) {
    this.dataSource = dataSource;
    return this;
  }
  
    /**
     *
     * @return
     */
    public List<Input> getInputList() {
    return inputList;
  }

    /**
     *
     * @param inputList
     * @return
     */
    public Datastream setInputList(List<Input> inputList) {
    this.inputList = inputList;
    return this;
  }

    /**
    *
    * @return
    */
	public String getTimePrecision() {
		return timePrecision;
	}

	/**
    *
    * @param timePrecision
    * @return
    */
	public Datastream setTimePrecision(String timePrecision) {
		this.timePrecision = timePrecision;
        return this;
	}

  /**
   *
   * @return
   */
  public String getLive() {
    return live;
  }

  /**
   *
   * @param live
   * @return
   */
  public void setLive(String live) {
    this.live = live;
  }
}
