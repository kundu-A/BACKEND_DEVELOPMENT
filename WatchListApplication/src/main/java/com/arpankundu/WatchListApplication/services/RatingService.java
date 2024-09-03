package com.arpankundu.WatchListApplication.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
public class RatingService {

	String apiUrl="https://omdbapi.com/?apikey=3030434d&t=";
	
	public String getMovieRating(String title) {
		try {
			RestTemplate template = new RestTemplate();
			ResponseEntity<ObjectNode> response = template.getForEntity(apiUrl+title, ObjectNode.class);
			ObjectNode objectNode=response.getBody();
			return objectNode.path("imdbRating").asText();
		}catch(Exception e) {
			System.out.println("Either movie is not available in the api or api is down"+e.getMessage());
			return null;
		}
	}
}
