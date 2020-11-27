Feature: To test the Current milli website

  Scenario: Convert Date in to millis
    Given User is on Page "URL"
    Then User Enter "2019" in field "rightYear|id"
    Then User Enter "06" in field "rightMonth|id"
    Then User Enter "30" in field "rightDay|id"
    Then User Takes a Screenshot
    Then User Verify the text of item "rightMillis|id" is "1561833000000"

  @RunNow
    Scenario: Search for a Vinery
      Given User is on Page "WINERY"
      Then User Enter "admin" in field "Username|id"
      And User Enter "welcome" in field "Password|id"
      Then User want to simulate the keyboard events "Tab,Tab,Enter"
      Then User Click on "Module_Admin_Name|id"
      And User Takes a Screenshot
    
    