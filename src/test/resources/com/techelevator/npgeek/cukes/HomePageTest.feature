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