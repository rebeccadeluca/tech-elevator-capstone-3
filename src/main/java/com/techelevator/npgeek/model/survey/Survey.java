package com.techelevator.npgeek.model.survey;

import org.springframework.stereotype.Component;

@Component
public class Survey {
	private String parkCode;
	private String email;
	private String state;
	private String activityLevel;

	public String getParkCode() {
		return parkCode;
	}
	public void setParkCode(String parkCode) {
		this.parkCode = parkCode;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getActivityLevel() {
		return activityLevel;
	}
	public void setActivityLevel(String activityLevel) {
		this.activityLevel = activityLevel;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Survey) {
			Survey survey = (Survey)obj;
			if(survey.getActivityLevel().equals(activityLevel) &&
			   survey.getEmail().equals(email) &&
			   survey.getParkCode().equals(parkCode) &&
			   survey.getState().equals(state)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
}
