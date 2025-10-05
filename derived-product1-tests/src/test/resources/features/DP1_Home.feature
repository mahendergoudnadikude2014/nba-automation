#Author: Mahender Nadikude
@sanity
Feature: Validate Slides on DP1 Home Page

  Scenario: Count slides, validate titles and durations under Tickets Menu on DP1 homepage
    Given I navigate to the DP1 homepage
    When I count the number of slides under Tickets Menu
    Then the slide count should be 5
    And I validate the slide titles with expected data
    And I validate the slide durations with expected data


