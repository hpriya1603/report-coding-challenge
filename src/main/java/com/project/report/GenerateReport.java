package com.project.report;

import java.util.List;

public class GenerateReport {

  public static void main(String[] args) {

    String input = args[0];

    ReportUtil reportUtil = new ReportUtil();

    List<Project> projectDetails = reportUtil.parseInputToList(input);

    System.out.println("The number of unique customerId for each contractId");
    System.out.println("---------------------------------------------------");

    reportUtil.uniqueCustIdByContract(projectDetails).entrySet()
      .stream()
      .forEach(m -> System.out.println("Contract ID: "+ m.getKey() + "  Number of Unique Customer Ids: " + m.getValue()));


    System.out.println("The number of unique customerId for each geozone");
    System.out.println("------------------------------------------------");

    reportUtil.uniqueCustIdByGeoZone(projectDetails).entrySet()
      .stream()
      .forEach(m -> System.out.println("Geo Zone: "+ m.getKey() + "  Number of Unique Customer Ids: " + m.getValue()));


    System.out.println("The average build duration for each geozone");
    System.out.println("------------------------------------------");

    reportUtil.avgBuildDuration(projectDetails).entrySet()
      .stream()
      .forEach(m -> System.out.println("Geo Zone: "+ m.getKey() + "  Average build durations: " + m.getValue()));


    System.out.println("The list of unique customerId for each geozone");
    System.out.println("----------------------------------------------");

    reportUtil.uniqueCustIdByZoneList(projectDetails).entrySet()
      .stream()
      .forEach(m -> System.out.println("Geo Zone: "+ m.getKey() + "  List of Unique Customer Ids: " + m.getValue()));

  }
}
