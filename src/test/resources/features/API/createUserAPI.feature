Feature: Create User API

  @createUser
Scenario: Create User
  When a request is prepared to make a POST call for creation of user
  And a POST call is made to create a user
  Then the status code is 201
  And the employee created contains the key "Message" and value "User Created"
  And the employee created contains the key "user.name" and value "Sora"
  And the employee created contains the key "user.email" and value "KingdomHeartssss2002@gmail.com"