Feature: Home Page Feature

Scenario: click on the survey link
	Given I am on the home page
	When I click the survey link
	Then I should be on the survey page
	
Scenario: clicking on an image
	Given I am on the home page
	When I click on a park image
	Then I should be on the park details page
	
Scenario: clicking on a park name
	Given I am on the home page
	When I click on a park name
	Then I should be on the park details page
	
Scenario: clicking on the logo
	Given I am on the home page
	When I click on the logo
	Then I should be on the home page
	
Scenario: going to the survey page
	Given I am on the home page
	When I click the survey link
	Then I should be on the survey page

Scenario: filling out a survey
	Given I am on the home page
	And I click on the survey link
	When I fill out the park
	And I fill out the email
	And I fill out the state
	And I fill out the activity level
	And I submit the survey form
	Then I should see the survey results page

Scenario: changing the temperature to celsius
	Given I am on the home page
	When I click on a park name
	And I change the temperature to celsius
	Then the temperature should be in celsius
	
Scenario: changing the temperature to fahrenheit
	Given I am on the home page
	And I am on a park detail page
	When I change the temperature to fahrenheit
	Then the temperature should be in fahrenheit
	
Scenario: View the survey results
	Given I am on the home page
	And I click on the survey link
	When I fill out and submit the survey
	Then I should see the survey results page
	And the display mode should be set to everyone
	
Scenario: View survey results similar to yours
	Given I am on the home page
	And I click on the survey link
	When I fill out and submit the survey
	And I change the displayMode to similar to you
	Then I should see the survey results page
	And the display mode should be set to demographics
	
Scenario: Fill out survey and click the survey link
	Given I am on the home page
	And I fill out the survey and submit the survey
	When I click on the survey link
	Then I should see the survey results page