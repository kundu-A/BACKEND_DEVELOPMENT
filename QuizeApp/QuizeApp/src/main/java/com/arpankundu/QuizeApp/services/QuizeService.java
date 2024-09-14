package com.arpankundu.QuizeApp.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.arpankundu.QuizeApp.models.QuestionWrapper;
import com.arpankundu.QuizeApp.models.Questions;
import com.arpankundu.QuizeApp.models.Quize;
import com.arpankundu.QuizeApp.models.Responses;
import com.arpankundu.QuizeApp.repository.QuestionRepo;
import com.arpankundu.QuizeApp.repository.QuizeRepo;

@Service
public class QuizeService {

	@Autowired
	QuizeRepo quizeRepo;
	
	@Autowired
	QuestionRepo questionRepo;
	
	public ResponseEntity<String> createQuize(String catagory , int numQ , String title){
		List<Questions> question = questionRepo.findRandomQuestionsByCatagory(catagory,numQ);
		
		Quize quize = new Quize();
		quize.setTitle(title);
		quize.setQuestions(question);
		quizeRepo.save(quize);
		
		return new ResponseEntity<>("Success" ,HttpStatus.CREATED);
		
	}

	public ResponseEntity<List<QuestionWrapper>> getQuizeQuestions(Integer id) {
		Optional<Quize> quize = quizeRepo.findById(id);
		List<Questions> questionFromDB=quize.get().getQuestions();
		List<QuestionWrapper> questionForUser = new ArrayList<>();
		for(Questions q : questionFromDB) {
			QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestion(), q.getOption1(), q.getOption2(), q.getOption3());
			questionForUser.add(qw);
		}
		
		return new ResponseEntity<>(questionForUser , HttpStatus.OK);
	}

	public ResponseEntity<Integer> calculateResponses(Integer id, List<Responses> responses) {
		Quize quize = quizeRepo.findById(id).get();
		List<Questions> questions = quize.getQuestions();
		int count=0 ,i=0;
		for(Responses response : responses ) {
			if(response.getResponse().equals(questions.get(i).getRightAnswer())) {
				count++;
			}
			i++;
		}
		return new ResponseEntity<>(count , HttpStatus.OK);
	}
}
