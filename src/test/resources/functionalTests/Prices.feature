Feature: Get Prices by date
  Description: To demonstrate the use of BDD style API automation

  Scenario: Get prices and check JSON schema
    Given The Prices API is live
    When I get prices using the following parameters "10", "LV", "16-08-2022", "17-08-2022"
    Then I receive 200 status code in response
    And Response has correct json schema "GET-200-price-data.json"

  Scenario Outline: Get prices data using valid parameters
    Given The Prices API is live
    When I get prices using the following parameters "<dno>", "<voltage>", "<start>", "<end>"
    Then I receive 200 status code in response
    And There is price data present

    Examples:
      | dno | voltage | start      | end        |
      | 10  | HV      | 16-08-2022 | 17-08-2022 |
      | 10  | LV      | 16-08-2022 | 17-08-2022 |
      | 10  | LV-Sub  | 16-08-2022 | 17-08-2022 |
      | 23  | HV      | 16-08-2022 | 17-08-2022 |
      | 23  | LV      | 16-08-2022 | 17-08-2022 |
      | 23  | LV-Sub  | 16-08-2022 | 17-08-2022 |

  Scenario Outline: Attempt to get prices data using invalid dno and voltage parameters
    Given The Prices API is live
    When I get prices using the following parameters "<dno>", "<voltage>", "<start>", "<end>"
    Then I receive 200 status code in response
    And There is no price data present
    # This should be HTTP 400 client error not 200

    Examples:
      | dno | voltage | start      | end        |
      | 09  | HV      | 16-08-2022 | 17-08-2022 |
      | 24  | HV      | 16-08-2022 | 17-08-2022 |
      | 10  | XX      | 16-08-2022 | 17-08-2022 |
      | 10  | 10      | 16-08-2022 | 17-08-2022 |

  Scenario: Attempt to get price data when missing "dno" parameter
    Given The Prices API is live
    When I get prices using the following parameters "", "LV", "16-08-2022", "17-08-2022"
    Then I receive 200 status code in response
    And Error message is "invalid literal for int() with base 10: ''"

  Scenario: Attempt to get price data when missing "voltage" parameter
    Given The Prices API is live
    When I get prices using the following parameters "10", "", "16-08-2022", "17-08-2022"
    Then I receive 200 status code in response
    And There is no price data present
    # This should be HTTP 400 client error not 200
    # The API returns an empty data array and not an error message
    # And Error message is "<error message not known>"

  Scenario: Attempt to get price data when missing "start date" parameter
    Given The Prices API is live
    When I get prices using the following parameters "10", "LV", "", "17-08-2022"
    Then I receive 200 status code in response
    And Error message is "time data '' does not match format '%d-%m-%Y'"

  Scenario: Attempt to get price data when missing "end date" parameter
    Given The Prices API is live
    When I get prices using the following parameters "10", "LV", "16-08-2022", ""
    Then I receive 200 status code in response
    And Error message is "time data '' does not match format '%d-%m-%Y'"

  Scenario: Get price data where start date is later than end date
    Given The Prices API is live
    When I get prices using the following parameters "10", "LV", "17-08-2022", "16-08-2022"
    Then I receive 200 status code in response
    And Error message is "The start_date parameter should not be after end_date"
    # This should be HTTP 400 client error not 200
    # The API returns an empty data array and not an error message

  Scenario: Get price data where start date is wrong format
    Given The Prices API is live
    When I get prices using the following parameters "10", "LV", "DD-MM-YYYY", "17-08-2022"
    Then I receive 200 status code in response
    And Error message is "time data 'DD-MM-YYYY' does not match format '%d-%m-%Y'"
    # This should be HTTP 400 client error not 200

  Scenario: Get price data where end date is wrong format
    Given The Prices API is live
    When I get prices using the following parameters "10", "LV", "16-08-2022", "DD-MM-YYYY"
    Then I receive 200 status code in response
    And Error message is "time data 'DD-MM-YYYY' does not match format '%d-%m-%Y'"
    # This should be HTTP 400 client error not 200
