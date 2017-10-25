package com.techelevator;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.techelevator.npgeek.model.park.JdbcParkDao;
import com.techelevator.npgeek.model.park.Park;

public class JdbcParkDaoIntegrationTest {

	private static SingleConnectionDataSource dataSource;
	private JdbcParkDao dao;
	private Park thisPark = new Park();;
	private JdbcTemplate jdbcTemplate;
	private List<Park> allParks;
	
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
		dao = new JdbcParkDao(dataSource);
		jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update("DELETE FROM weather");
		jdbcTemplate.update("DELETE FROM survey_result");
		jdbcTemplate.update("DELETE FROM park");
		jdbcTemplate.update("INSERT INTO park(parkCode, parkName, state, acreage, elevationInFeet, milesOfTrail, numberOfCampsites, climate, yearFounded, annualVisitorCount, inspirationalQuote, inspirationalQuoteSource, parkDescription, entryFee, numberOfAnimalSpecies) VALUES ('CVNP', 'Cuyahoga Valley National Park', 'Ohio', 32832, 696, 125, 0, 'Woodland', 2000, 2189849, 'Of all the paths you take in life, make sure a few of them are dirt.', 'John Muir', 'Summary', 0, 390);");
		allParks = new ArrayList<Park>();
		allParks.add(thisPark);
		thisPark.setImageName("cvnp");
		thisPark.setName("Cuyahoga Valley National Park");
		thisPark.setLocation("Ohio");
		thisPark.setSummary("Summary");
		thisPark.setCode("CVNP");
		thisPark.setAcreage(32832);
		thisPark.setElevation(696);
		thisPark.setMilesOfTrail(125);
		thisPark.setCampsites(0);
		thisPark.setClimate("Woodland");
		thisPark.setYearFounded(2000);
		thisPark.setVisitors(2189849);
		thisPark.setQuote("Of all the paths you take in life, make sure a few of them are dirt.");
		thisPark.setQuoteSource("John Muir");
		thisPark.setFee(0);
		thisPark.setSpecies(390);
	}

	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}

	protected DataSource getDataSource() {
		return dataSource;
	}
	
	@Test
	public void returns_all_parks() {
		List<Park> daoParks = dao.getAllParks();
		Assert.assertEquals(allParks, dao.getAllParks());
	}
}