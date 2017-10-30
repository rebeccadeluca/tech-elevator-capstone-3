package com.techelevator.npgeek.cukes;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;

import com.techelevator.npgeek.pageobject.HomePage;
import com.techelevator.npgeek.pageobject.ParkDetailPage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ParkDetailSteps {
	
	private WebDriver webDriver;
	private HomePage homePage;
	private ParkDetailPage detailPage;
	
	@Autowired
	public ParkDetailSteps(WebDriver webDriver) {
		this.webDriver = webDriver;
		this.homePage = homePage;
		this.detailPage = detailPage;
	}
	
//	@Given("^I am on the home page$")
//	public void go_to_home_page() {
//		webDriver.get("http://localhost:8080/m3-capstone-java/");
//		homePage.clickSurveyLink();
//	}
	
	
//	@Given("^I click on a park image$")
//	public void click_park_image() {
//		homePage.clickImageLink();
//	}
	
	@Given("^I am on a park detail page$")
	public void go_to_park_detail_page() {
		homePage.clickImageLink();
	}
	
	@When("^I change the temperature to (.*)$")
	public void change_units(String units) {
		detailPage.selectUnits(units);
	}
	
	@When("^I submit the form$")
	public void submit_form() {
		detailPage.submitNewUnits();
	}
	
	@Then("^the temperature should be in celsius$")
	public void should_see_temps_in_degrees_celsius() {
		Assert.assertTrue("We should be seeing the correct units", detailPage.isCelsius());
	}
	
	@Then("^the temperature should be in fahrenheit$")
	public void should_see_temps_in_degrees_fahrenheit() {
		Assert.assertTrue("We should be seeing the correct units", detailPage.isFahrenheit());
	}

}
