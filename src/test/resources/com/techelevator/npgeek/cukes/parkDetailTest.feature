Feature: Park Detail Page

Scenario: changing the temperature to celsius
	Given I am on a park detail page
	When I change the temperature to celsius
	And I submit the form
	Then the temperature should be in celsius
	
Scenario: changing the temperature to fahrenheit
	Given I am on a park detail page
	When I change the temperature to fahrenheit
	And I submit the form
	Then the temperature should be in fahrenheit
