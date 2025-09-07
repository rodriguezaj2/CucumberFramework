Feature: Creating Employee

  Background:
    Given JWT Token is generated

    @createEmployee
    Scenario:
      Given Request  is prepared to make a POST call to create an employee
      When POST call is made to create an employee
      Then The response code should be 201
