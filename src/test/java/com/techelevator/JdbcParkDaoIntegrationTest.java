package com.techelevator;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
	private Park thisPark;
	private JdbcTemplate jdbcTemplate;
	
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
		jdbcTemplate.update("DELETE FROM park");
		jdbcTemplate.update("INSERT INTO park (""))
	}

	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}
	
	@Test
	public void gets_length_of_stay() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date arrivalDate = sdf.parse("2017-11-20");
		Date departDate = sdf.parse("2017-11-23");
		BigDecimal length = dao.getLengthOfStay(arrivalDate, departDate);
		
		boolean areEqual = length.equals(new BigDecimal(3));
		
		Assert.assertTrue(areEqual);
	}
	
	
	/* This method provides access to the DataSource for subclasses so that 
	 * they can instantiate a DAO for testing */
	protected DataSource getDataSource() {
		return dataSource;
	}
	
	private Campsite makeACampsite(int siteId, int campgroundId, int siteNo, int maxOccup, boolean isAccessible, int maxRVLength, boolean hasUtilities) {
		Campsite thisCampsite = new Campsite();
		thisCampsite.setSiteId(siteId);
		thisCampsite.setCampgroundId(campgroundId);
		thisCampsite.setSiteNo(siteNo);
		thisCampsite.setMaxOccup(maxOccup);
		thisCampsite.setAccessible(isAccessible);
		thisCampsite.setMaxRVLength(maxRVLength);
		thisCampsite.setHasUtilities(hasUtilities);
		return thisCampsite;
	}
	
}