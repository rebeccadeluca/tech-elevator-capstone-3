package com.techelevator;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.techelevator.npgeek.model.survey.JdbcSurveyDao;
import com.techelevator.npgeek.model.survey.Survey;
import com.techelevator.npgeek.model.survey.SurveyResults;

public class JdbcSurveyDaoIntegrationTest {
	
	private static SingleConnectionDataSource dataSource;
	private JdbcSurveyDao dao;
	private Survey survey= new Survey();;
	private JdbcTemplate jdbcTemplate;
	private List<Survey> allSurveys = new ArrayList<Survey>();
	
	@BeforeClass
	public static void setupDataSource() {
		dataSource = new SingleConnectionDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/npgeek");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		dataSource.setAutoCommit(false);
	}
	
	@AfterClass
	public static void closeDataSource() throws SQLException {
		dataSource.destroy();
	}
	
	@Before
	public void setup() {
		dao = new JdbcSurveyDao(dataSource);
		jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update("DELETE FROM weather");
		jdbcTemplate.update("DELETE FROM survey_result");
		jdbcTemplate.update("DELETE FROM park");
		jdbcTemplate.update("INSERT INTO park(parkCode, parkName, state, acreage, elevationInFeet, milesOfTrail, numberOfCampsites, climate, yearFounded, annualVisitorCount, inspirationalQuote, inspirationalQuoteSource, parkDescription, entryFee, numberOfAnimalSpecies) VALUES ('CVNP', 'Cuyahoga Valley National Park', 'Ohio', 32832, 696, 125, 0, 'Woodland', 2000, 2189849, 'Of all the paths you take in life, make sure a few of them are dirt.', 'John Muir', 'Summary', 0, 390);");
		survey.setParkCode("CVNP");
		survey.setState("state");
		survey.setEmail("email");
		survey.setActivityLevel("activity");
		allSurveys.add(survey);
		dao.addSurvey(survey);
		Survey survey2 = new Survey();
		survey2.setParkCode("CVNP");
		survey2.setState("Ohio");
		survey2.setEmail("email");
		survey2.setActivityLevel("activity");
		allSurveys.add(survey2);
		dao.addSurvey(survey2);
		Survey survey3 = new Survey();
		survey3.setParkCode("CVNP");
		survey3.setState("Ohio");
		survey3.setEmail("email");
		survey3.setActivityLevel("activity");
		allSurveys.add(survey3);
		dao.addSurvey(survey3);
	}

	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}

	protected DataSource getDataSource() {
		return dataSource;
	}
	
	@Test
	public void should_get_all_surveys() {
		Assert.assertEquals(allSurveys, dao.getAllSurveys());
	}
	
	@Test
	public void should_add_survey() {
		Survey newSurvey = new Survey();
		newSurvey.setParkCode("CVNP");
		newSurvey.setState("Ohio");
		newSurvey.setActivityLevel("activity2");
		newSurvey.setEmail("email2");
		dao.addSurvey(newSurvey);
		allSurveys.add(newSurvey);
		Assert.assertEquals(allSurveys, dao.getAllSurveys());
	}
	
	@Test
	public void get_survey_results_should_be_returned() {
		SurveyResults results = dao.getSurveyResults();
		Assert.assertEquals("The count should be 3", 3, results.getCountForParkCode("CVNP").intValue());
	}
	
	@Test
	public void get_survey_results_should_return_only_similar_surveys() {
		SurveyResults results = dao.getSurveyResults(survey);
		Assert.assertEquals("The count should be 1", 1, results.getCountForParkCode("CVNP").intValue());
	}
}
