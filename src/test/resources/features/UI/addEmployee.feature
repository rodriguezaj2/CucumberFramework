Feature: Add Employee to HRMS Application

  Background:
    Scenario Outline:
    When user enters valid username "<usernameKey>" and password "<passwordKey>"
    And user clicks login button
    Then user is successfully logged in with dashboard showing "<expectedMessage>"

    Examples:
      | usernameKey      | passwordKey      | expectedMessage |
      | adminUserName    | adminPassword    | Dashboard       |
      | employeeUsername | employeePassword | Dashboard       |

      Scenario: