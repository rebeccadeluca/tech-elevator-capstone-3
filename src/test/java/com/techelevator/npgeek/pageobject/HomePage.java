package com.techelevator.npgeek.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

	private WebDriver webDriver;
	
	public HomePage(WebDriver webDriver) {
		this.webDriver = webDriver;
	}
	
	public ParkSurveyFormPage clickSurveyLink() {
		WebElement surveyLink = webDriver.findElement(By.linkText("Survey"));
		surveyLink.click();
		return new ParkSurveyFormPage(webDriver);
	}
	
	public ParkDetailPage clickImageLink() {
		WebElement imageLink = webDriver.findElement(By.id("park-img"));
		imageLink.click();
		return new ParkDetailPage(webDriver);
	}
	
	public ParkDetailPage clickParkNameLink(String parkName) {
		WebElement nameLink = webDriver.findElement(By.linkText(parkName));
		nameLink.click();
		return new ParkDetailPage(webDriver);
	}
}
