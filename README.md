# Project Title

WAES Technical Assessment Test

Author: Juan Torres

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for execution and testing purposes.

### Prerequisites

Gradle
Java 11
Java IDE preferable IntelliJ.

### Installing

Clone the project and execute the gradle build.

gradle build

If the test didn't pass (There's an issue with the gradle and Rest Assured local test)

gradle build -x test

Import the project in IntelliJ IDEA

Run Main class
WaesTechAssigmentApplication.java



End with an example of getting some data out of the system or using it for a little demo

## Running the tests

In the test package you can run the tests using the IDE execution runtime.

- DiffDataControllerTest

## Executing endpoints

With Postman or any other Rest client:

GET http://localhost:8080/v1/diff/{ID}

POST http://localhost:8080/v1/diff/1/left
   {
   	"data" : "stringBase64Encoded"
   }

POST http://localhost:8080/v1/diff/1/right
      {
      	"data" : "stringBase64Encoded"
      }