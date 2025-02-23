package com.arpankundu.WeatherApp.model;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class WeatherData {

	@JsonProperty("temp")
    private double temp;
	
	@JsonProperty("feelsLike")
    private double feelsLike;
	
	@JsonProperty("pressure")
    private long pressure;
	
	@JsonProperty("humidity")
    private long humidity;
	
	@JsonProperty("sunrise")
    private long sunriseTime;
	
	@JsonProperty("sunset")
    private long sunsetTime;
	
	@JsonProperty("speed")
    private double windSpeed;
	
	public double getTemp() {
		return temp;
	}
	public void setTemp(double temp) {
		this.temp = temp;
	}
	
	public double getFeelsLike() {
		return feelsLike;
	}
	public void setFeelsLike(double feelsLike) {
		this.feelsLike = feelsLike;
	}
	
	public long getPressure() {
		return pressure;
	}
	public void setPressure(long pressure) {
		this.pressure = pressure;
	}
	
	public long getHumidity() {
		return humidity;
	}
	public void setHumidity(long humidity) {
		this.humidity = humidity;
	}
	
	public long getSunriseTime() {
		return sunriseTime;
	}
	public void setSunriseTime(long sunriseTime) {
		this.sunriseTime = sunriseTime;
	}
	
	public long getSunsetTime() {
		return sunsetTime;
	}
	public void setSunsetTime(long sunsetTime) {
		this.sunsetTime = sunsetTime;
	}
	
	public double getWindSpeed() {
		return windSpeed;
	}
	public void setWindSpeed(double windSpeed) {
		this.windSpeed = windSpeed;
	}
}
