Scenario: click on the survey link
	Given I am on the home page
	When I click the survey link
	Then I should be on the survey page
	
Scenario: clicking on an image
	Given I am on the home page
	When I click on a park image
	Then I should be on the park details page