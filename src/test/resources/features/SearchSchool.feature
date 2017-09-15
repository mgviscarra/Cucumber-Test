Feature: Find a school match using Location and Major

Scenario: Find a school with 100% match

Given I navigate to College View web page
    And I Select the Start Searching button
When I Select Best Fit: "Location"
    And I Select Location: "Ohio"
    And I Select Best Fit: "Majors"
    And I Select degree: "Bachelor's (4-year) Degree" radio button
    And I set Major textbox with: "Computer Software Engineering"
    And I add Major: "Computer Software Engineering"
Then "Miami University-Oxford" university should be listed
    And Matching percentaje for university: "Miami University-Oxford" should be: "100%"