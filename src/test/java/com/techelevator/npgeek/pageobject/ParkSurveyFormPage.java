package com.techelevator.npgeek.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ParkSurveyFormPage {

	private WebDriver webDriver;
	
	public ParkSurveyFormPage(WebDriver webDriver) {
		this.webDriver = webDriver;
	}
	
	public ParkSurveyFormPage selectPark(String parkCode) {
		Select userPark = new Select(webDriver.findElement(By.id("park")));
		userPark.selectByValue(parkCode);
		return this;
	}
	
	public ParkSurveyFormPage enterEmailAddress(String email) {
		WebElement userEmail = webDriver.findElement(By.name("email"));
		userEmail.sendKeys(email);
		return this;
	}
	
	public ParkSurveyFormPage selectState(String state) {
		Select userState = new Select(webDriver.findElement(By.id("state")));
		userState.selectByValue(state);
		return this;
	}
	
	public ParkSurveyFormPage selectActivityLevel(String activityLevel) {
		List<WebElement> userActivityLevel = webDriver.findElements(By.name("activityLevel"));
		for(int i = 0; i < 4; i++) {
			String level = userActivityLevel.get(i).getAttribute("value");
			if(level.equalsIgnoreCase(activityLevel)) {
				userActivityLevel.get(i).click();
				break;
			}
		}
		return this;
	}
	
	public ParkSurveyResultsPage submitForm() {
		WebElement button = webDriver.findElement(By.id("submit-button"));
		button.click();
		return new ParkSurveyResultsPage(webDriver);
	}
	
	public boolean isShown() {
		try {
			WebElement form = webDriver.findElement(By.id("survey-form"));
			return form != null;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	
	public ParkSurveyResultsPage fillOutSurvey() {
		selectPark("CVNP");
		enterEmailAddress("email@email.com");
		selectState("Ohio");
		selectActivityLevel("extremely active");
		return submitForm();
	}
	 
}
