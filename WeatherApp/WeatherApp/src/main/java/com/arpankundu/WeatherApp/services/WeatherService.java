package com.arpankundu.WeatherApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.arpankundu.WeatherApp.DTO.WeatherResponse;
import com.arpankundu.WeatherApp.model.WeatherData;

@Service
public class WeatherService {

	private static final String apiKey="773654d59e2d2892f604b31876f2f682";
	private static final String URL="https://api.openweathermap.org/data/2.5/weather?q=CITY,COUNTRY&appid=KEY";
	
	@Autowired
	RestTemplate restTemplate;
	
	public WeatherData getWeatherInfo(String city,String country) {
		String finalUrl=URL.replace("CITY", city).replace("COUNTRY", country).replace("KEY", apiKey);
		ResponseEntity<WeatherResponse> response=restTemplate.exchange(finalUrl, HttpMethod.GET,null,WeatherResponse.class);
		 WeatherResponse weatherResponse = response.getBody();

	        WeatherData weatherData = new WeatherData();
	        weatherData.setTemp(weatherResponse.getMain().getTemp());
	        weatherData.setFeelsLike(weatherResponse.getMain().getFeelsLike());
	        weatherData.setPressure(weatherResponse.getMain().getPressure());
	        weatherData.setHumidity(weatherResponse.getMain().getHumidity());
	        weatherData.setSunriseTime(weatherResponse.getSys().getSunrise());
	        weatherData.setSunsetTime(weatherResponse.getSys().getSunset());
	        weatherData.setWindSpeed(weatherResponse.getWind().getSpeed());
	        
	        return weatherData;
	}
	
}
