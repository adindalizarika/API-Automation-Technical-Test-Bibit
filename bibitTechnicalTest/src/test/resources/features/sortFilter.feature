@sortfilter
Feature: Sort Filter

  Scenario: Verify user can product by name in descending
    Given User already on the catalog page
    And I click the sort button
    Then I should see the sort filter pop up
    When I click the sort by name in descending
    Then I should see the item sort by name in descending
    And I click the sort button again
    When I click the sort by price in ascending
    Then I should see the item sort by price in ascending
    