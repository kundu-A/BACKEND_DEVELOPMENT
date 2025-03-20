package com.arpankundu.DNeuro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arpankundu.DNeuro.models.Users;

@Repository
public interface PublicRepo extends JpaRepository<Users, Integer>{

	Users findByUsername(String name);

	Users findUsersByEmail(String email);

	boolean existsByEmail(String email);

}
