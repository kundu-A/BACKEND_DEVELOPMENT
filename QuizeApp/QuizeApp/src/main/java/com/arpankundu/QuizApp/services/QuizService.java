package com.arpankundu.QuizApp.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.arpankundu.QuizApp.models.QuestionWrapper;
import com.arpankundu.QuizApp.models.Questions;
import com.arpankundu.QuizApp.models.Quiz;
import com.arpankundu.QuizApp.models.Responses;
import com.arpankundu.QuizApp.repository.QuestionRepo;
import com.arpankundu.QuizApp.repository.QuizRepo;

@Service
public class QuizService {

	@Autowired
	QuizRepo quizeRepo;
	
	@Autowired
	QuestionRepo questionRepo;
	
	public ResponseEntity<String> createQuize(String catagory , int numQ , String title){
		List<Questions> question = questionRepo.findRandomQuestionsByCatagory(catagory,numQ);
		
		Quiz quize = new Quiz();
		quize.setTitle(title);
		quize.setQuestions(question);
		quizeRepo.save(quize);
		
		return new ResponseEntity<>("Success" ,HttpStatus.CREATED);
		
	}

	public ResponseEntity<List<QuestionWrapper>> getQuizeQuestions(Integer id) {
		Optional<Quiz> quize = quizeRepo.findById(id);
		List<Questions> questionFromDB=quize.get().getQuestions();
		List<QuestionWrapper> questionForUser = new ArrayList<>();
		for(Questions q : questionFromDB) {
			QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestion(), q.getOption1(), q.getOption2(), q.getOption3());
			questionForUser.add(qw);
		}
		
		return new ResponseEntity<>(questionForUser , HttpStatus.OK);
	}

	public ResponseEntity<Integer> calculateResponses(Integer id, List<Responses> responses) {
		Quiz quize = quizeRepo.findById(id).get();
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
