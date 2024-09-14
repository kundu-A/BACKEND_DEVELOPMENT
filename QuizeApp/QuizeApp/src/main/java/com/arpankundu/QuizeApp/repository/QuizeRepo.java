package com.arpankundu.QuizeApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arpankundu.QuizeApp.models.Quize;

@Repository
public interface QuizeRepo extends JpaRepository<Quize, Integer>{

}
