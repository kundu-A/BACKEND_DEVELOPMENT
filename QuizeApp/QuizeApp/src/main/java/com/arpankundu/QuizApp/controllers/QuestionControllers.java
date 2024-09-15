package com.arpankundu.QuizApp.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arpankundu.QuizApp.models.Questions;
import com.arpankundu.QuizApp.services.QuestionService;

@RestController
@RequestMapping("/questions")
public class QuestionControllers {

	@Autowired
	QuestionService questionService;
	
	@GetMapping("/getquestions")
	public ResponseEntity<List<Questions>> getAllQuestions() {
		return questionService.getAllQuestions();
	}
	@GetMapping("/getquestion/{id}")
	public ResponseEntity<Optional<Questions>> getQuestionById(@PathVariable Integer id) {
		return questionService.getQuestionById(id);
	}
	@GetMapping("/getquestions/{catagory}")
	public ResponseEntity<List<Questions>> findQuestionByCatagory(@PathVariable String catagory) {
		return questionService.findQuestionByCatagory(catagory);
	}
	@GetMapping("/getquestion/{catagory}/{id}")
	public ResponseEntity<Questions> findQuestionByCatagoryAndId(@PathVariable String catagory , @PathVariable Integer id) {
		return questionService.findQuestionByCatagoryAndId(catagory,id);
	}
	@PostMapping("/setquestion")
	public ResponseEntity<Questions> createQuestions(@RequestBody Questions question) {
		return questionService.createQuestions(question);
	}
	@PostMapping("/setquestions")
	public ResponseEntity<String> createQuestionSet(@RequestBody List<Questions> question) {
		return questionService.createQuestionSet(question);
	}
	@PutMapping("/updatequestion/{id}")
	public ResponseEntity<String> updateQuestion(@RequestBody Questions question , @PathVariable Integer id) {
		return questionService.updateQuestion(question ,id);
	}
	@DeleteMapping("/deletequestion/{id}")
	public ResponseEntity<String> deleteQuestion(@PathVariable Integer id) {
		return questionService.deleteQustion(id);
	}
	@DeleteMapping("/deletequestion/{catagory}/{id}")
	public ResponseEntity<String> deleteQuestionByCatagoryAndId(@PathVariable String catagory , @PathVariable Integer id) {
		return questionService.deleteQuestionByCatagoryAndId(catagory,id);
	}
}
