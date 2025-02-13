package com.arpankundu.journalApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arpankundu.journalApp.models.Email;

@Repository
public interface EmailRepository extends JpaRepository<Email, Integer>{

}
