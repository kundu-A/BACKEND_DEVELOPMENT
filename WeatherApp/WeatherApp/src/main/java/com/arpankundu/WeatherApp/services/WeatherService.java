package com.arpankundu.WeatherApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

	String apiKey="773654d59e2d2892f604b31876f2f682";
	String apiUrl="https://api.openweathermap.org/data/2.5/weather";
	@Autowired
	RestTemplate restTemplate;
	public String getWeatherInfo(String city,String country) {
		String url=String.format("%s?q=%s,%s&appid=%s",apiUrl,city,country,apiKey);
		return restTemplate.getForObject(url, String.class);
	}
}
