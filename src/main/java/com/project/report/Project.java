package com.project.report;

public class Project {

  private Integer customerId;
  private Integer contractId;
  private String geoZone;
  private String teamCode;
  private String projectCode;
  private Integer buildDuration;

  public Project(Integer customerId, Integer contractId, String geoZone, String teamCode, String projectCode, Integer buildDuration) {
    this.customerId = customerId;
    this.contractId = contractId;
    this.geoZone = geoZone;
    this.teamCode = teamCode;
    this.projectCode = projectCode;
    this.buildDuration = buildDuration;
  }


  public Integer getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Integer customerId) {
    this.customerId = customerId;
  }

  public Integer getContractId() {
    return contractId;
  }

  public void setContractId(Integer contractId) {
    this.contractId = contractId;
  }

  public int getBuildDuration() {
    return buildDuration;
  }

  public void setBuildDuration(int buildDuration) {
    this.buildDuration = buildDuration;
  }

  public String getGeoZone() {
    return geoZone;
  }

  public void setGeoZone(String geoZone) {
    this.geoZone = geoZone;
  }

  public String getTeamCode() {
    return teamCode;
  }

  public void setTeamCode(String teamCode) {
    this.teamCode = teamCode;
  }

  public String getProjectCode() {
    return projectCode;
  }

  public void setProjectCode(String projectCode) {
    this.projectCode = projectCode;
  }


  @Override
  public String toString() {
    return "ProjectDetails{" +
      "customerId=" + customerId +
      ", contractId=" + contractId +
      ", geoZone='" + geoZone + '\'' +
      ", teamCode='" + teamCode + '\'' +
      ", projectCode='" + projectCode + '\'' +
      ", buildDuration=" + buildDuration +
      '}';
  }
}
