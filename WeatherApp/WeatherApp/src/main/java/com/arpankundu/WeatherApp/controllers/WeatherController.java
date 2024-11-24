package com.arpankundu.WeatherApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arpankundu.WeatherApp.services.WeatherService;

@RestController
@RequestMapping("/weather")
public class WeatherController {

	@Autowired
	WeatherService weatherService;
	
	@GetMapping("/greet")
	public String Greet() {
		return "Hello , Welcome the Weather Application!!";
	}
	@GetMapping("/weatherinfo")
	public ResponseEntity<?> getWeatherInfo(@RequestParam String city,@RequestParam String country){
		try {
			return new ResponseEntity<>(weatherService.getWeatherInfo(city,country),HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
