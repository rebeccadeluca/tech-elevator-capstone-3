package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.techelevator.npgeek.model.survey.Survey;
import com.techelevator.npgeek.model.survey.SurveyResults;


public class SurveyResultsTest {
	private SurveyResults results;
	
	@Before
	public void setup()	{
		results = new SurveyResults();
		Survey survey = new Survey();
		survey.setActivityLevel("activity");
		survey.setEmail("email");
		survey.setParkCode("CVNP");
		survey.setState("state");
		results.addSurvey(survey);
	}
	
	@Test
	public void survey_results_should_return_right_number() {
		Assert.assertEquals("There should be 1", results.getCountForParkCode("CVNP").intValue(), 1);
	}
	
	
}
