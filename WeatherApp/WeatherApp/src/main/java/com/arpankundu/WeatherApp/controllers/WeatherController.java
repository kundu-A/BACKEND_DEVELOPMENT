 package com.arpankundu.WeatherApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arpankundu.WeatherApp.model.Location;
import com.arpankundu.WeatherApp.model.WeatherData;
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
	@PostMapping("/location-wise-weather")
	public ResponseEntity<?> getWeather(@RequestBody Location location){
		try {
			String city=location.getCity();
			String country=location.getCountry();
			WeatherData response=weatherService.getWeatherInfo(city,country);
			if(response!=null)
				return new ResponseEntity<>(response,HttpStatus.OK);
			return new ResponseEntity<>("Data not found",HttpStatus.BAD_REQUEST);
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>("Some internal issues!!",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
