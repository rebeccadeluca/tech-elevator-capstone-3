package com.techelevator.npgeek.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.techelevator.npgeek.model.park.ParkDao;

@Controller
public class HomeController {
	@Autowired
	private ParkDao dao;

	@RequestMapping("/")
	public String displayHomePage(HttpServletRequest request) {
		request.setAttribute("parks", dao.getAllParks());
		return "homePage";
	}
}
