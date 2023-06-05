Feature: Scenarios for create booking Api

  Scenario: Create a booking and test HTTP response code
    Given we have a booking request
      | firstname | lastname | additionalneeds | depositpaid | checkout   | checkin    | totalprice |
      | Jerry     | Helpert  | mineral water   | true        | 2024-02-02 | 2024-01-01 | 100        |

    When  we send the request to create booking api
    Then  HTTP response status code should be 200


  Scenario Outline: Create another booking with different name
    Given we have a booking request
      | firstname   | lastname   | additionalneeds   | depositpaid   | checkout   | checkin   | totalprice   |
      | <firstname> | <lastname> | <additionalneeds> | <depositpaid> | <checkout> | <checkin> | <totalprice> |

    When  we send the request to create booking api
    Then  HTTP response status code should be <statusCode>
    Examples:
      | firstname | lastname | additionalneeds | depositpaid | checkin    | checkout   | totalprice | statusCode |
      | Jerry     | Helpert  | mineral water   | true        | 2024-02-02 | 2024-01-01 | 100        | 200        |
      | Jerry     | Helpert  | mineral water   | true        | 2024-02-02 | 2024-01-01 | 000        | 200        |
      |           | Helpert  | mineral water   | true        | 2024-02-02 | 2024-01-01 | 100        | 500        |