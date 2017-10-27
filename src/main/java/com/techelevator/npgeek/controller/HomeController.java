package com.techelevator.npgeek.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.techelevator.npgeek.model.park.ParkDao;
import com.techelevator.npgeek.model.survey.Survey;
import com.techelevator.npgeek.model.survey.SurveyDao;
import com.techelevator.npgeek.model.weather.WeatherDao;

@Controller
@SessionAttributes({"unit", "userSurvey", "surveyDisplayMode"})
public class HomeController {
	@Autowired
	private ParkDao parkDao;
	@Autowired
	private WeatherDao weatherDao;
	@Autowired
	private SurveyDao surveyDao;

	@RequestMapping("/")
	public String displayHomePage(HttpServletRequest request) {
		request.setAttribute("parks", parkDao.getAllParks());
		return "homePage";
	}
	
	@RequestMapping(path="/parkDetail", method=RequestMethod.GET)
	public String displayParkDetailPage(HttpServletRequest request, @RequestParam String parkCode, ModelMap map) {
		request.setAttribute("forecast", weatherDao.getFiveDayForcast(parkCode));
		request.setAttribute("park", parkDao.getParkByCode(parkCode));
		if(!map.containsKey("unit")) {
			map.put("unit", "fahrenheit");
		}
		return "parkDetail";
	}
	
	@RequestMapping(path="/parkDetail", method=RequestMethod.POST)
	public String changeUnits(@RequestParam String unit, @RequestParam String parkCode, ModelMap map) {
		map.put("unit", unit);
		return "redirect:/parkDetail?parkCode=" + parkCode;
	}
	
	@RequestMapping(path="/parkSurvey", method=RequestMethod.GET)
	public String getSurveyForm(ModelMap map, HttpServletRequest request) {
		if (map.containsKey("userSurvey")) {
			return "redirect:/partSurveyResults";
		} else {
			request.setAttribute("parks", parkDao.getAllParks());
			String[] states = {"California", "Alabama", "Arkansas", "Arizona", "Alaska", "Colorado", "Connecticut", "Delaware", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York", "North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming"};
			request.setAttribute("states", states);
			return "parkSurveyForm";
		}
	}
	
	@RequestMapping(path="/parkSurvey", method=RequestMethod.POST)
	public String getSurveyResults(ModelMap map, Survey survey) {
		surveyDao.addSurvey(survey);
		map.addAttribute("userSurvey", survey);
		return "redirect:/parkSurveyResults";
	}
	
	@RequestMapping(path="/parkSurveyResults", method=RequestMethod.GET)
	public String showSurveyResults(ModelMap map, HttpServletRequest request) {
		String displayMode = (String) map.getOrDefault("surveyDisplayMode", "everyone");
		if (displayMode.equals("everyone")) {
			request.setAttribute("surveyResults", surveyDao.getSurveyResults());
		} else if (displayMode.equals("demographics")) {
			request.setAttribute("surveyResults", surveyDao.getSurveyResults((Survey)map.get("userSurvey")));
		}
		return "parkSurveyResults";
	}
	
	@RequestMapping(path="/parkSurveyResults", method=RequestMethod.POST)
	public String showActivityLevel(@RequestParam String displayMode, ModelMap map) {
			map.addAttribute("surveyDisplayMode", displayMode);
		return "redirect:/parkSurveyResults";
	}
}
