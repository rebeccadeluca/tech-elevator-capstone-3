package com.techelevator.npgeek.pageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class ParkDetailPage {

	private WebDriver webDriver;
	
	public ParkDetailPage(WebDriver webDriver) {
		this.webDriver = webDriver;
	}
	
	public ParkDetailPage selectUnits(String tempUnit) {
//		List<WebElement> units = webDriver.findElements(By.name("unit"));
//		for(int i = 0; i < 2; i++) {
//			String unit = units.get(i).getAttribute("value");
//			if(unit.equals(tempUnit)) {
//				units.get(i).click();
//				break;
//			}
//		}
		String css = "input[value='" + tempUnit + "']";
		WebElement unit = webDriver.findElement(By.cssSelector(css));
		WebElement button = webDriver.findElement(By.id("submit"));
//		unit.click();
		
		JavascriptExecutor jse = (JavascriptExecutor)webDriver;
		jse.executeScript("arguments[0].checked = true; arguments[1].click()", unit, button);
		
//		WebElement button = webDriver.findElement(By.id("submit"));
//		button.click();
		return this;

//		for(int i = 0; i < 2; i++) {
//			String unit = userChoice.get(i).getAttribute("value");
//			if(unit.equalsIgnoreCase(tempUnit)) {
//				userChoice.get(i).click();
//				break;
//			}
//		}
//		return this;
	}
	
	public boolean isShown() {
		try {
			WebElement form = webDriver.findElement(By.id("park-detail"));
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
		WebElement unitsShown = webDriver.findElement(By.id("temp"));
		if(unitsShown.getText().contains("C")) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isFahrenheit() {
		WebElement unitsShown = webDriver.findElement(By.id("temp"));
		if(unitsShown.getText().contains("F")) {
			return true;
		} else {
			return false;
		}
	}

}
