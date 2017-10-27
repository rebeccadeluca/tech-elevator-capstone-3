package com.techelevator.npgeek.pageobject;

import java.util.List;

import org.openqa.selenium.By;
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

}
