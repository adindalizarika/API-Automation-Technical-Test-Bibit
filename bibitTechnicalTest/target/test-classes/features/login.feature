@login
Feature: Login

  Scenario: User logs in to the account
    Given I am on the catalog page
    And I click the sidebar button
    Then I should see the sidebar
    When I click the login button
    Then I should see the login form
    When I fill in the login form with valid credentials
    And I click the submit button
    Then I should see the homepage
    And I want to see logout button