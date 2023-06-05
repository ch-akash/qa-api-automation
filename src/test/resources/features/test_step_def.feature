@feature-level-tag @regression @requires-db-connection
Feature: Test cucumber step definitions

  @first-test-tag @bug @smoke @flaky
  Scenario: Test sample cucumber scenario - Print Hello World
    Given I have a sample code to run
    When  I print "Hello World"
    And   I print "Hello World"
    Then  I verify "Hello World" is printed
    And   I will check if status code is 200

  Scenario: Test sample cucumber scenario - Print Hello World 2
    Given I have a sample code to run
    When  I print "Hello World 2"
    And   I print "Hello World 2"
    Then  I verify "Hello World 2" is printed
    And   I will check if status code is 201