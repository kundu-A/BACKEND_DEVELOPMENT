package com.arpankundu.QuizeApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.arpankundu.QuizeApp.models.Questions;

@Repository
public interface QuestionRepo extends JpaRepository<Questions, Integer>{

	List<Questions> findByCatagory(String catagory);

	Questions findByCatagoryAndId(String catagory, Integer id);

	void deleteByCatagoryAndId(String catagory, Integer id);

	@Query(value = "SELECT * FROM questions_table q where q.catagory_of_questions=:catagory ORDER BY RAND() LIMIT :numQ" , nativeQuery=true )
	List<Questions> findRandomQuestionsByCatagory(String catagory, int numQ);

	
}
