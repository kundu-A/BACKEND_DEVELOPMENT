package com.arpankundu.QuizApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.arpankundu.QuizApp.models.Questions;

@Repository
public interface QuestionRepo extends JpaRepository<Questions, Integer>{

	List<Questions> findByCatagory(String catagory);
	//Here it is a simple query to find questions from database that's why we doesn't need any @Query annotation .
	Questions findByCatagoryAndId(String catagory, Integer id);
	//Same for here also.
	void deleteByCatagoryAndId(String catagory, Integer id);
	//Here we want to fetch specific question or write some complex query that's why we need @Query.
	@Query(value = "SELECT * FROM questions_table q where q.catagory_of_questions=:catagory ORDER BY RAND() LIMIT :numQ" , nativeQuery=true )
	List<Questions> findRandomQuestionsByCatagory(String catagory, int numQ);

	
}
