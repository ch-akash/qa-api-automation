Feature: Test scenario for the our sample Api

  @PRJ-1233 @bug
  Scenario: Create a new student
    Given a new student has been created with following request
      | name | id   | email        |
      | john | 999  | john@aol.com |
      | lisa | 1001 | lisa@aol.com |

  @create-multiple-wth-example
  Scenario Outline: Create new student
    Given a new student has been created with following request
      | name   | id   | email   |
      | <name> | <id> | <email> |

    Examples:
      | name    | id    | email          |
      | john    | test  | test@gmail.com |
      | list    | 12121 | lisa@gmail.com |
      | jacob   | 9219  | lisa@gmail.com |
      | john111 | test  | test@gmail.com |
      | john    | 12212 | test-gmail.com |