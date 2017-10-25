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
import com.techelevator.npgeek.model.weather.WeatherDao;

@Controller
@SessionAttributes("unit")
public class HomeController {
	@Autowired
	private ParkDao parkDao;
	@Autowired
	private WeatherDao weatherDao;

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
}
