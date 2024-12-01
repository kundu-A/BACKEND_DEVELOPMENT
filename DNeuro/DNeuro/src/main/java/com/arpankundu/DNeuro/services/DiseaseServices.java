package com.arpankundu.DNeuro.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arpankundu.DNeuro.models.Disease;
import com.arpankundu.DNeuro.repository.DiseaseRepo;

import jakarta.transaction.Transactional;

@Service
public class DiseaseServices {

	@Autowired
	DiseaseRepo diseaseRepo;
	
	@Transactional
	public List<Disease> setDiseaseDetails(List<Disease> diseases) {
		return diseaseRepo.saveAll(diseases);
	}

	@Transactional
	public Disease setOneDisease(Disease disease) {
		return diseaseRepo.save(disease);
	}

	public List<Disease> getDiseaseDetails() {
		return diseaseRepo.findAll();
	}

}
