#Author: Mahender Nadikude
@sanity
Feature: DP2 Home Page Footer Validation

  Scenario: Validate footer hyperlinks on DP2 homepage
    Given I navigate to the DP2 homepage
    When I scroll down to the footer section
    Then I extract all footer hyperlinks
    And I write footer links to CSV file and check for duplicates



