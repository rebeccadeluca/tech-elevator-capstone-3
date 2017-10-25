package com.techelevator.npgeek.model.weather;

import java.util.ArrayList;
import java.util.List;

import com.techelevator.npgeek.model.park.Park;

public class Weather {

	private String code;
	private int day;
	private int low;
	private int high;
	private String forecast;	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getLow(String unit) {
		if(unit.equals("fahrenheit")) {
			return low;
		} else {
			return (int)((5.0/9.0)*(low - 32));
		}
	}
	public void setLow(int low) {
		this.low = low;
	}
	public int getHigh(String unit) {
		if(unit.equals("fahrenheit")) {
			return high;
		} else {
			return (int)((5.0/9.0)*(high - 32));
		}
	}
	public void setHigh(int high) {
		this.high = high;
	}
	public String getForecast() {
		return forecast;
	}
	public void setForecast(String forecast) {
		this.forecast = forecast;
	}
	
	public List<String> getRecommendation() {
		List<String> recommendations = new ArrayList<String>();
		if(forecast.equals("cloudy")) {
			recommendations.add("Pack snowshoes");
		}
		if(forecast.equals("rainy")) {
			recommendations.add("Pack raingear and wear waterproof shoes");
		}
		if(forecast.equalsIgnoreCase("thunderstorms")) {
			recommendations.add("Seek shelter and avoid hiking on exposed ridges");
		}
		if(forecast.equals("sunny")) {
			recommendations.add("Pack sunblock");
		}
		if(high > 75) {
			recommendations.add("Bring an extra gallon of water");
		}
		if(high - low > 20) {
			recommendations.add("Wear breatheable layers");
		}
		if(low < 20) {
			recommendations.add("Caution! Frigid temperatures can cause hypothermia");
		}
		return recommendations;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Weather) {
			Weather weather = (Weather) obj;
			if (weather.getDay() == day && weather.getForecast().equals(forecast) && weather.getHigh("fahrenheit") == high && weather.getLow("fahrenheit") == low && weather.getCode().equals(code)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
}
