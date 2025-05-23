package com.arpankundu.journalApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.arpankundu.journalApp.models.Users;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<Users, Integer>{

	Users findUsersByUsername(String username);

	Users findUsersByEmail(String email);
	
	boolean existsByEmail(String email);


}
