package com.techelevator.npgeek.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.techelevator.npgeek.model.park.ParkDao;
import com.techelevator.npgeek.model.weather.WeatherDao;

@Controller
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
	
	@RequestMapping("/parkDetail")
	public String displayParkDetailPage(HttpServletRequest request, @RequestParam String parkCode) {
		request.setAttribute("forecast", weatherDao.getFiveDayForcast(parkCode));
		request.setAttribute("park", parkDao.getParkByCode(parkCode));
		return "parkDetail";
	}
}
