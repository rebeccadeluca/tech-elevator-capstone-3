package com.techelevator.npgeek.cukes;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;

import com.techelevator.npgeek.pageobject.HomePage;
import com.techelevator.npgeek.pageobject.ParkDetailPage;
import com.techelevator.npgeek.pageobject.ParkSurveyFormPage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class HomePageSteps {

	private WebDriver webDriver;
	private HomePage homePage;
	private ParkSurveyFormPage surveyFormPage;
	private ParkDetailPage detailPage;
	
	@Autowired
	public HomePageSteps(WebDriver webDriver) {
		this.webDriver = webDriver;
		this.homePage = homePage;
		this.surveyFormPage = surveyFormPage;
		this.detailPage = detailPage;		
	}
	
	@Given("^I am on the home page$")
	public void go_to_home_page() {
		webDriver.get("http://localhost:8080/m3-capstone-java/");
		homePage.clickSurveyLink();
	}
	
	@When("^I click the survey link$")
	public void click_suvery_link() {
		homePage.clickSurveyLink();
	}
	
	@When("^I click on a park image$")
	public void click_on_park_image() {
		homePage.clickImageLink();
	}
	
	@When("^I click on a park name$")
	public void click_park_name() {
		homePage.clickParkNameLink();
	}
	
	@When("^I click on the logo$")
	public void click_on_logo() {
		homePage.clickLogo();
	}
	
	@Then("^I should be on the survey page$")
	public void should_be_on_survey_page() {
		Assert.assertTrue("We should be on the survey form page", surveyFormPage.isShown());
	}
	
	@Then("^I should be on the park details page$")
	public void should_be_on_park_details_page() {
		Assert.assertTrue("We should be on the park details page", detailPage.isShown());
	}
	
	@Then("^I should be on the home page$")
	public void should_be_on_home_page() {
		Assert.assertTrue("We should be on the home page", homePage.isShown());
	}
}
