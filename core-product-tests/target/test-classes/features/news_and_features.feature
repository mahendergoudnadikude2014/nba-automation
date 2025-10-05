#Author: Mahender Nadikude
@sanity
Feature: New and Featured Videos

  Scenario: Count videos and those older than 3 days on New & Features page
    Given I am on the Core Product homepage
    When I hover on menu item and click on New & Features
    Then I count total number of video feeds
    And I count the video feeds those are present in the page >= 3d
    Then the total feeds count should be greater than or equal to the count of 3 days old feeds