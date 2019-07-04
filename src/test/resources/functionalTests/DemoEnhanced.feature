Feature: To get the value from milli second
  Scenario: Convert date to millliseconds
    Given User is on Page "URL"
    And User Verify the text of item "CurrentDateLocator" is "CurrentDate"
    Then User Takes a Screenshot
    When User Enter "Year" in field "YearLocator"
    And User Enter "Month" in field "MonthLocator"
    And User Enter "Day" in field "DayLocator"
    And User want wait "2" seconds
    And User Takes a Screenshot
    And User Verify the text of item "MilliValueLocator" is "MilliSecondValue"


