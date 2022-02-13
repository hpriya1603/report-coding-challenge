package com.project.report;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

public class ReportUtilTest {

  List<Project> testList = new ArrayList<>();

  @Before
  public void init() {

    testList = Arrays.asList(new Project(2343225,2345,"us_east","RedTeam","ProjectApple",3445),
                             new Project(1223456,2345,"us_west","BlueTeam","ProjectBanana",2211),
                             new Project(3244332,2346,"eu_west","YellowTeam3","ProjectCarrot",4322),
                             new Project(1223456,2345,"us_west","BlueTeam","ProjectDate",2221),
                             new Project(3244332,2346,"eu_west","YellowTeam3","ProjectEgg",4122));
  }

  @Test
  public void parseNullInput() {
     ReportUtil reportUtil = new ReportUtil();
     assertTrue(reportUtil.parseInputToList(null)== null || reportUtil.parseInputToList(null).isEmpty());
  }

  @Test
  public void checkParsedData() {
    ReportUtil reportUtil = new ReportUtil();
    List<Project> list = reportUtil.parseInputToList("2343225,2345,us_east,RedTeam,ProjectApple,3445s");

    assertEquals( 2343225, list.get(0).getCustomerId().intValue());
    assertEquals(2345, list.get(0).getContractId().intValue());
    assertEquals("us_east", list.get(0).getGeoZone());
    assertEquals("RedTeam", list.get(0).getTeamCode());
    assertEquals("ProjectApple", list.get(0).getProjectCode());
    assertEquals(3445, list.get(0).getBuildDuration());
  }

  @Test
  public void checkParsedDataWithGaps() {
    ReportUtil reportUtil = new ReportUtil();
    List<Project> list = reportUtil.parseInputToList("2343225,2345,us_east,,ProjectApple,3445s");
    assertTrue(list.get(0).getTeamCode() == null | list.get(0).getTeamCode().isEmpty());
  }

  @Test(expected=NumberFormatException.class)
  public void checkParsedDataWithDiffDataType() throws NumberFormatException {
    ReportUtil reportUtil = new ReportUtil();
    List<Project> list = reportUtil.parseInputToList("2343225a,2345,us_east,,ProjectApple,3445s");
    fail("The data type is different in the input String");
  }


  @Test
  public void parseInputToList() {
    ReportUtil reportUtil = new ReportUtil();
    String inputString = "2343225,2345,us_east,RedTeam,ProjectApple,3445s\n" +
      "1223456,2345,us_west,BlueTeam,ProjectBanana,2211s\n" +
      "3244332,2346,eu_west,YellowTeam3,ProjectCarrot,4322s\n" +
      "1233456,2345,us_west,BlueTeam,ProjectDate,2221s\n" +
      "3244132,2346,eu_west,YellowTeam3,ProjectEgg,4122s";

    assertEquals(5, reportUtil.parseInputToList(inputString).size());
  }

  @Test
  public void uniqueCustIdByContract() {
    ReportUtil reportUtil = new ReportUtil();
    Map<Integer, Integer> uniqueCustomerIds = reportUtil.uniqueCustIdByContract(testList);
    assertEquals( 2, uniqueCustomerIds.get(2345).intValue());
    assertEquals(1,  uniqueCustomerIds.get(2346).intValue());
  }

  @Test
  public void uniqueCustIdByGeoZone() {
    ReportUtil reportUtil = new ReportUtil();
    Map<String, Integer> uniqueCustomerIds = reportUtil.uniqueCustIdByGeoZone(testList);
    assertEquals( 1, uniqueCustomerIds.get("us_east").intValue());
    assertEquals( 1, uniqueCustomerIds.get("us_west").intValue());
    assertEquals( 1, uniqueCustomerIds.get("eu_west").intValue());
  }

  @Test
  public void uniqueCustIdByZoneList() {
    ReportUtil reportUtil = new ReportUtil();
    Map<String, Set<Integer>> uniqueCustomerIds = reportUtil.uniqueCustIdByZoneList(testList);
    assertEquals( 1, uniqueCustomerIds.get("us_east").size());
    assertEquals( 1, uniqueCustomerIds.get("us_west").size());
    assertEquals( 1,uniqueCustomerIds.get("eu_west").size());
  }

  @Test
  public void avgBuildDuration() {
    ReportUtil reportUtil = new ReportUtil();
    Map<String, Double> uniqueCustomerIds = reportUtil.avgBuildDuration(testList);
    assertEquals( 3445.0, uniqueCustomerIds.get("us_east").doubleValue(), 0.0);
    assertEquals( 2216.0, uniqueCustomerIds.get("us_west").doubleValue(), 0.0);
    assertEquals( 4222.0, uniqueCustomerIds.get("eu_west").doubleValue(), 0.0);
  }
}