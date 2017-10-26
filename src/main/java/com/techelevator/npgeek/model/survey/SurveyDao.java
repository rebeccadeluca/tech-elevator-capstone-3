package com.techelevator.npgeek.model.survey;

import java.util.List;

public interface SurveyDao {

	public void addSurvey(Survey survey);
	public SurveyResults getSurveyResults();
}
