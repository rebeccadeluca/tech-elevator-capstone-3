package com.techelevator.npgeek.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ParkSurveyResultsPage {
	
	private WebDriver webDriver;
	
	public ParkSurveyResultsPage(WebDriver webDriver) {
		this.webDriver = webDriver;
	}
	
	public ParkSurveyResultsPage selectResultsType(String resultsType) {
		List<WebElement> userChoice = webDriver.findElements(By.name("displayMode"));
		for(int i = 0; i < 2; i++) {
			String unit = userChoice.get(i).getAttribute("value");
			if(unit.equalsIgnoreCase(resultsType)) {
				userChoice.get(i).click();
				break;
			}
		}
		return this;
	}

}
