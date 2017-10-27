package com.techelevator.npgeek.model.survey;

import org.springframework.jdbc.datasource.SingleConnectionDataSource;

public class SurveyResponseGenerator {

	private static JdbcSurveyDao dao;
	
	private static String[] parkCodes = new String[] {"CVNP", "ENP", "GCNP", "GNP", "GSMNP", "GTNP", "MRNP", "RMNP", "YNP", "YNP2"};
	private static String[] activityLevel = new String[] {"sedentary", "inactive", "active", "extremely active"};
	private static String[] states = new String[] {"California", "Alabama", "Arkansas", "Arizona", "Alaska", "Colorado", "Connecticut", "Delaware", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York", "North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming"};
	
	public static void main(String[] args) {
		SingleConnectionDataSource dataSource = new SingleConnectionDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/npgeek");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		dao = new JdbcSurveyDao(dataSource);
		for(int i = 0; i < 100000; i++) {
			Survey survey = new Survey();
			String email = "";
			survey.setEmail(email);
			int codeNumber = (int) (Math.random()*parkCodes.length);
			int activityNumber = (int) (Math.random()*activityLevel.length);
			int statesNumber = (int) (Math.random()*states.length);
			survey.setActivityLevel(activityLevel[activityNumber]);
			survey.setParkCode(parkCodes[codeNumber]);
			survey.setState(states[statesNumber]);
			dao.addSurvey(survey); 
		}
	}
}
