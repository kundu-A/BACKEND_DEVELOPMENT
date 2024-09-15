package com.arpankundu.QuizApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arpankundu.QuizApp.models.Quiz;

@Repository
public interface QuizRepo extends JpaRepository<Quiz, Integer>{

}
