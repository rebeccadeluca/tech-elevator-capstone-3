package com.techelevator.npgeek.model.park;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcParkDao implements ParkDao {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JdbcParkDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Park> getAllParks() {
		List<Park> allParks = new ArrayList<Park>();
		String sqlSelectAllParks = "SELECT * FROM park";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectAllParks);
		while(results.next()) {
			allParks.add(mapRowToPark(results));
		} 
		return allParks;
	}

	private Park mapRowToPark(SqlRowSet results) {
		Park park = new Park();
		park.setImageName(results.getString("parkCode"));
		park.setName(results.getString("parkName"));
		park.setLocation(results.getString("state"));
		park.setSummary(results.getString("parkDescription"));
		park.setCode(results.getString("parkCode"));
		park.setAcreage(results.getInt("acreage"));
		park.setElevation(results.getInt("elevationInFeet"));
		park.setMilesOfTrail(results.getFloat("milesOfTrail"));
		park.setCampsites(results.getInt("numberOfCampsites"));
		park.setClimate(results.getString("climate"));
		park.setYearFounded(results.getInt("yearFounded"));
		park.setVisitors(results.getInt("annualVisitorCount"));
		park.setQuote(results.getString("inspirationalQuote"));
		park.setQuoteSource(results.getString("inspirationalQuoteSource"));
		park.setFee(results.getInt("entryFee"));
		park.setSpecies(results.getInt("numberOfAnimalSpecies"));
		return park;
	}

	@Override
	public Park getParkByCode(String code) {
		Park park = new Park();
		String sqlQuery = "SELECT * FROM park WHERE parkCode = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlQuery, code);
		while(results.next()) {
			park = mapRowToPark(results);
		} 
		return park;
	}

}
