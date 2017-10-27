package com.techelevator.npgeek.model.survey;


import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.npgeek.model.park.JdbcParkDao;

@Component
public class JdbcSurveyDao implements SurveyDao{
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JdbcSurveyDao(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override 
	public SurveyResults getSurveyResults() {
		SurveyResults surveyResults = new SurveyResults();
		String sqlQuery = "SELECT * FROM survey_result";
		
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sqlQuery);
		while(rows.next()) {
			Survey survey = mapRowToSurvey(rows);
			surveyResults.addSurvey(survey);
		}
		return surveyResults;
	}
	
	@Override
	public SurveyResults getSurveyResults(Survey survey) {
		SurveyResults surveyResults = new SurveyResults();
		String sqlQuery = "SELECT * FROM survey_result WHERE state = ? AND activityLevel = ?";
		
		SqlRowSet rows = jdbcTemplate.queryForRowSet(sqlQuery, survey.getState(), survey.getActivityLevel());
		while(rows.next()) {
			Survey newSurvey = mapRowToSurvey(rows);
			surveyResults.addSurvey(newSurvey);
		}
		return surveyResults;
	}

	public List<Survey> getAllSurveys() {
		List<Survey> allSurveys = new ArrayList<Survey>();
		String sqlQuery = "SELECT * FROM survey_result";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlQuery);
		while(results.next()) {
			Survey survey = new Survey();
			survey = mapRowToSurvey(results);
			allSurveys.add(survey);
		}
		return allSurveys;
	}
	
	@Override
	public void addSurvey(Survey survey) {
		String sqlQuery = "INSERT INTO survey_result(surveyId, parkCode, emailAddress, state, activityLevel) VALUES(?, ?, ?, ?, ?)";
		int id = getNextSurveyId();
		jdbcTemplate.update(sqlQuery, id, survey.getParkCode(), survey.getEmail(), survey.getState(), survey.getActivityLevel());
	}
	
	private Survey mapRowToSurvey(SqlRowSet row) {
		Survey survey = new Survey();
		survey.setParkCode(row.getString("parkCode"));
		survey.setEmail(row.getString("emailAddress"));
		survey.setState(row.getString("state"));
		survey.setActivityLevel(row.getString("activityLevel"));
		return survey;
	}
	
	private int getNextSurveyId() {
		SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet("SELECT nextval('seq_surveyId')");
		if(nextIdResult.next()) {
			return nextIdResult.getInt(1);
		} else {
			throw new RuntimeException("Something went wrong while getting an id for the next reservation");
		}
	}
}
