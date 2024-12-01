package com.arpankundu.DNeuro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arpankundu.DNeuro.models.Disease;

@Repository
public interface DiseaseRepo extends JpaRepository<Disease, Integer>{

}
