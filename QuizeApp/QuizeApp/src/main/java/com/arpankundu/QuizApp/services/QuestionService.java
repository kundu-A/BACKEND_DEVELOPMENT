package com.arpankundu.QuizApp.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.arpankundu.QuizApp.models.Questions;
import com.arpankundu.QuizApp.repository.QuestionRepo;

import jakarta.transaction.Transactional;

@Service
public class QuestionService {

	@Autowired
	QuestionRepo questionRepo;
	//Get Method
	public ResponseEntity<List<Questions>> getAllQuestions() {
		try {
			return new ResponseEntity<>(questionRepo.findAll(),HttpStatus.OK);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
	}
	//Create Method
	public ResponseEntity<Questions> createQuestions(Questions question) {
		try {
			return new ResponseEntity<>(questionRepo.save(question),HttpStatus.CREATED);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
	}
	//Create Method
	public ResponseEntity<String> createQuestionSet(List<Questions> question) {
		try {
			questionRepo.saveAll(question);
			return new ResponseEntity<>("Questions are successfully added into database",HttpStatus.CREATED);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return new ResponseEntity<>("Questions are not successfully added",HttpStatus.BAD_REQUEST);
	}
	//Get Method
	public ResponseEntity<Optional<Questions>> getQuestionById(Integer Id) {
		try {
			return new ResponseEntity<>(questionRepo.findById(Id),HttpStatus.OK);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
	}
	//Update Method
	public ResponseEntity<String> updateQuestion(Questions question,Integer id) {
		try {
			Questions questionToBeUpdated = questionRepo.findById(id).get();
			questionToBeUpdated.setCatagory(question.getCatagory());
			questionToBeUpdated.setLevel(question.getLevel());
			questionToBeUpdated.setQuestion(question.getQuestion());
			questionToBeUpdated.setOption1(question.getOption1());
			questionToBeUpdated.setOption2(question.getOption2());
			questionToBeUpdated.setOption3(question.getOption3());
			questionToBeUpdated.setRightAnswer(question.getRightAnswer());
			questionRepo.save(questionToBeUpdated);
			return new ResponseEntity<>("Question updated successfully",HttpStatus.CREATED);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return new ResponseEntity<>("Question not updated successfully",HttpStatus.BAD_REQUEST);
	}
	//Delete Method
	public ResponseEntity<String> deleteQustion(Integer id){
		try {
			questionRepo.deleteById(id);
			return new ResponseEntity<>("Question deleted successfully",HttpStatus.OK);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return new ResponseEntity<>("Question not deleted successfully",HttpStatus.BAD_REQUEST);
	}
	//Get Method
	public ResponseEntity<List<Questions>> findQuestionByCatagory(String catagory) {
		try {
			return new ResponseEntity<>(questionRepo.findByCatagory(catagory),HttpStatus.OK);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
	}
	//Get Method
	public ResponseEntity<Questions> findQuestionByCatagoryAndId(String catagory, Integer id) {
		try {
			return new ResponseEntity<>(questionRepo.findByCatagoryAndId(catagory ,id),HttpStatus.OK);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
	}
	//Delete Method
	@Transactional
	public ResponseEntity<String> deleteQuestionByCatagoryAndId(String catagory, Integer id) {
		try {
			questionRepo.deleteByCatagoryAndId(catagory,id);
			return new ResponseEntity<>("This question is deleted successfully",HttpStatus.OK);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return new ResponseEntity<>("This question is not deleted successfully",HttpStatus.BAD_REQUEST);
	}
}
