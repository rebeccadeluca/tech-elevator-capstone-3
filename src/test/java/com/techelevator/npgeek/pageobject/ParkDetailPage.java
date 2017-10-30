package com.techelevator.npgeek.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ParkDetailPage {

	private WebDriver webDriver;
	
	public ParkDetailPage(WebDriver webDriver) {
		this.webDriver = webDriver;
	}
	
	public ParkDetailPage selectUnits(String tempUnit) {
		List<WebElement> userChoice = webDriver.findElements(By.name("unit"));
		for(int i = 0; i < 2; i++) {
			String unit = userChoice.get(i).getAttribute("value");
			if(unit.equalsIgnoreCase(tempUnit)) {
				userChoice.get(i).click();
				break;
			}
		}
		return this;
	}
	
	public boolean isShown() {
		try {
			WebElement form = webDriver.findElement(By.id("survey-form"));
			return form != null;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	
	public ParkDetailPage submitNewUnits() {
		WebElement submitButton = webDriver.findElement(By.id("submit"));
		submitButton.click();
		return this;
	}

	public boolean isCelsius() {
		WebElement unitsShown = webDriver.findElement(By.id("C"));
		if(unitsShown.getText().contains("C")) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isFahrenheit() {
		WebElement unitsShown = webDriver.findElement(By.id("F"));
		if(unitsShown.getText().contains("F")) {
			return true;
		} else {
			return false;
		}
	}

}
