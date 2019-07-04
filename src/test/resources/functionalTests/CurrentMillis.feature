Feature: To test the Current milli website
  @RunNow
  Scenario: Convert Date in to millis
    Given User is on Page "URL"
    Then User Enter "2019" in field "rightYear|id"
    Then User Enter "06" in field "rightMonth|id"
    Then User Enter "30" in field "rightDay|id"
    Then User Takes a Screenshot
    Then User Verify the text of item "rightMillis|id" is "1561833000000"
    
    