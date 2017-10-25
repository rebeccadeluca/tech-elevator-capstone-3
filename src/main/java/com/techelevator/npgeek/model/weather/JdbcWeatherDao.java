package com.techelevator.npgeek.model.weather;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcWeatherDao implements WeatherDao {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JdbcWeatherDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Weather> getFiveDayForcast(String parkCode) {
		List<Weather> forecast = new ArrayList<Weather>();
		String sqlForecast = "SELECT * FROM weather WHERE parkCode = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlForecast, parkCode);
		while(results.next()) {
			forecast.add(mapRowToWeather(results));
		} 
		return forecast;
	}

	private Weather mapRowToWeather(SqlRowSet results) {
		Weather weather = new Weather();
		weather.setLow(results.getInt("low"));
		weather.setHigh(results.getInt("high"));
		weather.setForecast(results.getString("forecast"));
		weather.setDay(results.getInt("fiveDayForecastValue"));
		weather.setCode(results.getString("parkCode"));
		return weather;
	}

}
