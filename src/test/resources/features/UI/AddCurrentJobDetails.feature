@ui
Feature:

  Background:
    Given user is logged in successfully using "admin" and "Hum@nhrm123"
    When user navigates to Employee List

    Scenario: Job title field is displayed and editable for admin user
      Given