package com.arpankundu.QuizApp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arpankundu.QuizApp.models.QuestionWrapper;
import com.arpankundu.QuizApp.models.Responses;
import com.arpankundu.QuizApp.services.QuizService;

@RestController
@RequestMapping("/quiz")
public class QuizController {

	@Autowired
	QuizService quizeService;
	
	@PostMapping("/create")
	public ResponseEntity<String> createQuize(@RequestParam String catagory ,@RequestParam int numQ, @RequestParam String title){
		return quizeService.createQuize(catagory, numQ, title);
	}
	@GetMapping("/get/{id}")
	public ResponseEntity<List<QuestionWrapper>> getQuestions(@PathVariable Integer id){
		return quizeService.getQuizeQuestions(id);
	}
	@PostMapping("/submit/{id}")
	public ResponseEntity<Integer> submitQuize(@PathVariable Integer id ,@RequestBody List<Responses> responses){
		return quizeService.calculateResponses(id,responses);
	}
}
