Scenario: going to the survey page
	Given I am on the home page
	When I click the survey link
	Then I should see the survey form page

Scenario: filling out a survey
	Given I am on the home page
	And I click on the survey link
	When I fill out the park
	and I fill out the email
	and I fill out the state
	and I fill out the activity level
	and I submit the form
	Then I should see the survey results page