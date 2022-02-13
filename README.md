## Project Statistics Report

This application provides a report on the following:

- The number of unique customerId for each contractId
- The number of unique customerId for each geozone
- The average buildduration for each geozone
- The list of unique customerId for each geozone

The input is a comma delimited multiline String in the following format

``customerId,contractId,geozone,teamcode,projectcode,buildduration``

### Approach

- Write a program that accepts multi-line string as input through command line argument.
- Call the corresponding functions to obtain different elements of the report.
- Using Streams API, group the attributes based on `Geozone` or `ContractId`
  and filter the unique `Customer IDs` by writing them to a Set.
- Iteratively print the report for each attribute of the group.

### Assumptions

- The attributes are of the following Data types:

``````Customer Id : Integer
 Contract Id : Integer
 Geozone : String
 Teamcode : String
 Projectcode : String
 Build duration : Integer (in Seconds)
``````
- The input is a single multi-line String.
- The attributes are not nullable.

### Specifications 

- Java 8
- Junit 4.12
- Gradle 7.0

### Installation

To run this application, run the following commands in the command prompt/terminal, from the root folder of the project

```
./gradlew clean && ./gradlew build
```

```
./gradlew runReport -Pargs="2343225,2345,us_east,RedTeam,ProjectApple,3445s
1223456,2345,us_west,BlueTeam,ProjectBanana,2211s
3244332,2346,eu_west,YellowTeam3,ProjectCarrot,4322s
1233456,2345,us_west,BlueTeam,ProjectDate,2221s
3244132,2346,eu_west,YellowTeam3,ProjectEgg,4122s"

```
**Note:**  The multiline input String to be given between `""` in `-Pargs=`
