Scenario: changing the temperature
	Given I am on the hompe page
	And I click on a park image
	When I change the temparture to celsius
	And I submit the form
	Then the temperature should be in celsius