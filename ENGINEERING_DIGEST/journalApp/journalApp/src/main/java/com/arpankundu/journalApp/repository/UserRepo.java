package com.arpankundu.journalApp.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.arpankundu.journalApp.models.Users;

@Repository
public interface UserRepo extends JpaRepository<Users, Integer>{

	Users findUsersByUsername(String username);

	/*@Query("SELECT u.username, u.role FROM Users u")
	List<?> findUsernameAndRole();*/
}
