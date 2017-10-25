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

import com.techelevator.npgeek.model.weather.JdbcWeatherDao;
import com.techelevator.npgeek.model.weather.Weather;

public class JdbcWeatherDaoIntegrationTest {

	private static SingleConnectionDataSource dataSource;
	private JdbcWeatherDao dao;
	private JdbcTemplate jdbcTemplate;
	private List<Weather> allWeather = new ArrayList<Weather>();
	
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
		dao = new JdbcWeatherDao(dataSource);
		jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update("DELETE FROM weather");
		jdbcTemplate.update("DELETE FROM survey_result");
		jdbcTemplate.update("DELETE FROM park");
		jdbcTemplate.update("INSERT INTO park(parkCode, parkName, state, acreage, elevationInFeet, milesOfTrail, numberOfCampsites, climate, yearFounded, annualVisitorCount, inspirationalQuote, inspirationalQuoteSource, parkDescription, entryFee, numberOfAnimalSpecies) VALUES ('GNP', 'Glacier National Park', 'Montana', 1013322, 6646, 745.6, 923, 'Temperate', 1910, 2338528, 'Far away in northwestern Montana, hidden from view by clustering mountain peaks, lies an unmapped corner—the Crown of the Continent.', 'George Bird Grinnell', 'Glacier might very well be the most beautiful of America''s national parks. John Muir called it the best care-killing scenery on the continent. The mountains are steep, snowcapped, and punctuated by stunning mountain lakes and creeks. Much of the land remains wild and pristine, a result of its remote location and the lack of visitation in the 19th century.  ', 12, 300)");
		jdbcTemplate.update("INSERT INTO weather(parkCode, fiveDayForecastValue, low, high, forecast) VALUES ('GNP',1,27,40,'snow')");
		jdbcTemplate.update("INSERT INTO weather(parkCode, fiveDayForecastValue, low, high, forecast) VALUES ('GNP',2,27,40,'snow')");
		jdbcTemplate.update("INSERT INTO weather(parkCode, fiveDayForecastValue, low, high, forecast) VALUES ('GNP',3,27,40,'snow')");
		jdbcTemplate.update("INSERT INTO weather(parkCode, fiveDayForecastValue, low, high, forecast) VALUES ('GNP',4,27,40,'snow')");
		jdbcTemplate.update("INSERT INTO weather(parkCode, fiveDayForecastValue, low, high, forecast) VALUES ('GNP',5,27,40,'snow')");
		for(int i = 1; i < 6; i++) {
			Weather weather = new Weather();
			weather.setCode("GNP");
			weather.setDay(i);
			weather.setForecast("snow");
			weather.setHigh(40);
			weather.setLow(27);
			allWeather.add(weather);
		}
		
	}

	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}

	protected DataSource getDataSource() {
		return dataSource;
	}
	
	@Test
	public void returns_forecast() {
		List<Weather> daoWeather = dao.getFiveDayForcast("GNP");
		Assert.assertEquals(allWeather, daoWeather);
	}
}