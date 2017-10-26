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
		List<Survey> list = new ArrayList<Survey>();
		list.add(survey);
		list.add(newSurvey);
		Assert.assertEquals(list, dao.getAllSurveys());
	}
	
	@Test
	public void survey_results_should_be_returned() {
		SurveyResults expectedResults = new SurveyResults();
		expectedResults.addSurvey(survey);
		boolean rightNumberOfSurveys = expectedResults.getCountForPark("CVNP") == 1;
		Assert.assertTrue("There should be only CVNP", rightNumberOfSurveys);
	}
}
