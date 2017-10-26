package com.techelevator.npgeek.model.survey;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.stereotype.Component;

import com.techelevator.npgeek.model.park.JdbcParkDao;
import com.techelevator.npgeek.model.park.Park;
@Component
public class SurveyResults {
	private JdbcParkDao parkDao;
	private Map<String, Integer> countForPark = new HashMap<String, Integer>();
	private Map<String, Park> codeToPark = new HashMap<String, Park>();
	
	public SurveyResults() {
		SingleConnectionDataSource dataSource = new SingleConnectionDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/npgeek");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		dataSource.setAutoCommit(false);
		parkDao = new JdbcParkDao(dataSource);
	}
	
	public Integer getCountForParkCode(String parkCode) {
		return countForPark.get(parkCode);
	}
	
	public void addSurvey(Survey survey) {
		//increment countForPark
		if (countForPark.containsKey(survey.getParkCode())) {
			countForPark.put(survey.getParkCode(), countForPark.get(survey.getParkCode())+ 1);
		} else {
			countForPark.put(survey.getParkCode(), 1);
			//add park to codeToPark
			codeToPark.put(survey.getParkCode(), parkDao.getParkByCode(survey.getParkCode()));
		}
	}
	
	public List<String> getAllParkCodes(){
		List<String> unsortedCodes = new ArrayList<String>(countForPark.keySet());
		
		for(int i = 1; i < unsortedCodes.size(); i++) {
	        int valueToSort = countForPark.get(unsortedCodes.get(i));
	        String actualValue = unsortedCodes.get(i);
	        int indexToCompare = i - 1;
	        while(indexToCompare >= 0 && countForPark.get(unsortedCodes.get(indexToCompare)) < valueToSort) {
	            unsortedCodes.set(indexToCompare + 1, unsortedCodes.get(indexToCompare));
	            indexToCompare--;
	        }
	        unsortedCodes.set(indexToCompare + 1, actualValue);
	    }
		return unsortedCodes;
	}
	
	public String getName(String parkCode) {
		return codeToPark.get(parkCode).getName();
	}
	
}
