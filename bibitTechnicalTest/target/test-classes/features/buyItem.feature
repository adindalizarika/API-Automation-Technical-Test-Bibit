@buyitem
Feature: Buy Item

  Scenario: Verify user can buy item
    Given User already login to the account
    And I click first item
    Then I should see the item detail
    When I scroll on the item detail page
    Then I should see the add to card button
    When I click the increment button
    Then I should see the item becomes two
    And I click the add to cart button
    And I click the cart button
    Then I should see the checkout detail page
    And I click the proceed to checkout button
    Then I should see the checkout form page
    And I enter the full name field
    And I enter the address name 1 field
    And I enter the address name 2 field
    And I enter the city field
    And I enter the state or region field
    And I enter the zip code field
    And I enter the country field
    And I scroll until find the payment button
    And I click the to payment button
    Then I should see the payment page
    And I enter the full name payment field
    And I enter the card number field
    And I enter the expiration date field
    And I enter the security code field
    And I click the review order button
    Then I should see the review order page
    And I click the place order button
    Then I should see the checkout complete page
    And I click the continue shopping button
    Then I should see the catalog page
