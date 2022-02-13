package com.project.report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.averagingInt;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toSet;

public class ReportUtil
{

  // Parses multi line string to List <Project>
  public List<Project> parseInputToList(String input) throws NumberFormatException{

    List<Project> projectDetails = Stream.of(input.split("\n"))     //Stream <String>
      .map(e -> e.split(","))  //Stream <String[]>
      .map(s -> new Project(Integer.parseInt(s[0]), Integer.parseInt(s[1]), s[2], s[3], s[4], Integer.parseInt(s[5].replaceAll("\\D+", "")))) //Stream <Project>
      .collect(Collectors.toList()); //List <Project>

    return projectDetails;
  }

  //Provides no. of unique customers for each contract Id, in a given list
  public Map<Integer, Integer> uniqueCustIdByContract(List<Project> list) {

    Map<Integer, Integer> result = new HashMap<>();
    try {
      result = list.stream()
        .collect(groupingBy(Project::getContractId,  // Groups by contract Id
          mapping(Project::getCustomerId,           // fetch only cust id
            collectingAndThen(toSet(), Set::size))  // Collecting to Set, to remove duplicates and returning the size
          )
        );
    }
    catch (Exception e){
      e.printStackTrace();
    }

    return result;
  }

  //Provides no. of unique customers for each GeoZone, in a given list
  public Map<String, Integer> uniqueCustIdByGeoZone(List<Project> list) {

    Map<String, Integer> result = new HashMap<>();
    try {
      result = list.stream()
        .collect(groupingBy(Project::getGeoZone,     // Groups by Geo zone
          mapping(Project::getCustomerId,           // fetch only cust id
            collectingAndThen(toSet(), Set::size)) // Collecting to Set, to remove duplicates and returning the size
          )
        );
    }
    catch (Exception e){
      e.printStackTrace();
    }

    return result;
  }

  //Provides set of unique customers for each geozone, in a given list
  public Map<String, Set<Integer>> uniqueCustIdByZoneList(List<Project> list) {

    Map<String, Set<Integer>> result = new HashMap<>();
    try {
      result = list.stream()
        .collect(groupingBy(Project::getGeoZone,  // Groups by Geo zone
          mapping(Project::getCustomerId,         // fetch only cust id
            toSet())                              // Collecting to Set
          )
        );
    }
    catch (Exception e){
      e.printStackTrace();
    }

    return result;
  }

  //Provides avg of build duration for each GeoZone, in a given list
  public Map<String, Double> avgBuildDuration(List<Project> list) {

    Map<String, Double> avgBuild = new HashMap<>();
    try {
      avgBuild = list.stream()
        .collect(groupingBy(Project::getGeoZone,    // Groups by Geo zone
          averagingInt(Project::getBuildDuration)   // Computes avg of build duration
          )
        );
    }
    catch (Exception e){
      e.printStackTrace();
    }

    return avgBuild;
  }

}
