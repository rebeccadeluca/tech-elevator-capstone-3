Scenario: View the survey results
	Given I am on the homepage
	And I fill out the survey
	When I click to submit the survey
	Then I should see the survey results page
	And the display mode should be set to everyone
	
Scenario: View survey results similar to yours
	Given I am on the homepage
	And I fill out the survey
	And I submit the survey
	When I change the displayMode to similar to you
	Then I should see the survey results page
	and the display mode should be set to demographics
	
Scenario: Fill out survey and click the survey link
	Given I am on the homepage
	And I fill out the survey
	And I click to submit the survey
	When I click on the survey link
	Then I should see the survey results page