package com.techelevator.npgeek.cukes;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;

import com.techelevator.npgeek.pageobject.HomePage;
import com.techelevator.npgeek.pageobject.ParkDetailPage;
import com.techelevator.npgeek.pageobject.ParkSurveyFormPage;
import com.techelevator.npgeek.pageobject.ParkSurveyResultsPage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class HomePageSteps {

	private WebDriver webDriver;
	private HomePage homePage;
	private ParkSurveyFormPage surveyFormPage;
	private ParkDetailPage detailPage;
	private ParkSurveyResultsPage resultsPage;
	
	@Autowired
	public HomePageSteps(WebDriver webDriver) {
		this.webDriver = webDriver;
		this.homePage = new HomePage(webDriver);
		this.surveyFormPage = new ParkSurveyFormPage(webDriver);
		this.detailPage = new ParkDetailPage(webDriver);	
		this.resultsPage = new ParkSurveyResultsPage(webDriver);
	}
	
	@Given("^I am on the home page$")
	public void go_to_home_page() {
		webDriver.get("http://localhost:8080/m3-capstone-java/");
	}
	
	@Given("^I am on a park detail page$")
	public void go_to_park_detail_page() {
		homePage.clickImageLink();
	}
	
	@Given("^I click on the survey link$")
	public void go_to_survey_page() {
		homePage.clickSurveyLink();
	}
	
	@When("^I fill out and submit the survey$")
	public void fill_out_survey_form() {
		surveyFormPage.fillOutSurvey();
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
	
	@When("^I fill out the park$")
	public void fill_out_park_in_survey() {
		surveyFormPage.selectPark("CVNP");
	}
	
	@When("^I fill out the email$")
	public void fill_out_the_email() {
		surveyFormPage.enterEmailAddress("email@email.com");
	}
	
	@When("^I fill out the state$")
	public void fill_out_the_state() {
		surveyFormPage.selectState("Ohio");
	}
	
	@When("^I fill out the activity level$")
	public void fill_out_activity_level() {
		surveyFormPage.selectActivityLevel("inactive");
	}
	
	@When("^I submit the survey form$")
	public void submit_survey_form() {
		surveyFormPage.submitForm();
	}
	
	@When("^I click on the logo$")
	public void click_on_logo() {
		homePage.clickLogo();
	}
	
	@When("^I change the temperature to (.*)$")
	public void change_units(String units) {
		detailPage.selectUnits(units);
	}
	
	@When("^I submit the form$")
	public void submit_form() {
		detailPage.submitNewUnits();
	}
	
	@When("^I change the displayMode to similar to you$")
	public void change_survey_display_mode() {
		resultsPage.selectResultsType("demographics");
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
	
	@Then("^the temperature should be in celsius$")
	public void should_see_temps_in_degrees_celsius() {
		Assert.assertTrue("We should be seeing the correct units", detailPage.isCelsius());
	}
	
	@Then("^the temperature should be in fahrenheit$")
	public void should_see_temps_in_degrees_fahrenheit() {
		Assert.assertTrue("We should be seeing the correct units", detailPage.isFahrenheit());
	}
	
	@Then("^I should see the survey results page$")
	public void should_see_survey_results_page() {
		Assert.assertTrue("We should be seeing the survey results page", resultsPage.isShown());
	}
	
	@Then("^the display mode should be set to demographics$")
	public void should_see_results_for_demographics() {
		Assert.assertTrue("We should be seeing results for demographics", resultsPage.isDemographics());;
	}
	
	@Then("^the display mode should be set to everyone$")
	public void should_see_results_for_everyone() {
		Assert.assertTrue("We should be seeing results for everyone", resultsPage.isEveryone());
	}
}
