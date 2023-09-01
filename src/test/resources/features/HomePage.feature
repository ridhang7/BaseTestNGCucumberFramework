@letsgoo
Feature: Verify home page display

  Background: Launch URL and Login

  @letsgoo1
  Scenario: Launch URL and Login
    And I login valid using credentials
    And I Click on Products
    Then Verify Product page is displayed

  @letsgoo2
  Scenario: Launch URL and Login
    And I login valid using credentials
    And I Click on service
    Then Verify Service page is displayed