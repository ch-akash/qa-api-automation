@crud
Feature: Scenario to test CRUD flow

  Scenario: Test for end to end CRUD operation
    Given we have a booking request
      | firstname | lastname | additionalneeds | depositpaid | checkout   | checkin    | totalprice |
      | Jerry     | Helpert  | mineral water   | true        | 2024-02-02 | 2024-01-01 | 100        |

    When  we send the request to create booking api
    Then  HTTP response status code should be 200
    And   validate that response has bookingid

    When  store the booking from create response
    When  we "retrieve" the previously created booking
    Then  HTTP response status code should be 200

    When  we "update" the previously created booking
    Then  HTTP response status code should be 200

    When  we "delete" the previously created booking
    Then  HTTP response status code should be 201
