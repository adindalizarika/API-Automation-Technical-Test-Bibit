Feature: Logout

  Scenario: Logout from the application
    Given I am on the home page
    And I click the profile button
    Then I should see the profile page
    And I scroll to the bottom of the page
    And I click the logout button
    Then I should see the login button