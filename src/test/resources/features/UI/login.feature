Feature: login functionality

  @login
 Scenario Outline: valid login
   When user enters valid username "<usernameKey>" and password "<passwordKey>"
   And user clicks login button
   Then user is successfully logged in with dashboard showing "<expectedMessage>"

    Examples:
      | usernameKey      | passwordKey      | expectedMessage |
      | adminUserName    | adminPassword    | Dashboard       |
      | employeeUsername | employeePassword | Dashboard       |

  @invalidLogin
  Scenario: invalid login
    When user enters invalid username and password
    And user clicks login button
    Then user sees error message "Invalid credentials"

  @emptyUserName
  Scenario: login with empty username
    When user leaves username field empty
    And user enters password
    And user clicks login button
    Then user sees error message "Username cannot be empty"

  @emptyPassword
  Scenario: login with empty password
    When user enters username
    And user leaves password field empty
    And user clicks login button
    Then user sees error message "Password is Empty"