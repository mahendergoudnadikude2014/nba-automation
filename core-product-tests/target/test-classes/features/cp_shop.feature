#Author: Mahender Nadikude
@sanity
Feature: Core Product Shop - Jackets Listing

  Scenario: List all jackets with details across paginated pages
    Given I am on the Core Product homepage
    When I navigate to Shop > Mens section
    And I select Jackets in the Departments list
    Then I collect all Jackets Title, Price, and Top Seller message from all pages
    And save details to a text file and attach it to the report

