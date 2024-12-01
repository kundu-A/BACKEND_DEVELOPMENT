package com.arpankundu.DNeuro.services;

import java.util.List;
import java.util.Optional;

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

	@Transactional
	public List<Disease> getDiseaseDetails() {
		return diseaseRepo.findAll();
	}

	@Transactional
	public Optional<Disease> getOneDisease(Integer id) {
		return diseaseRepo.findById(id);
	}
	
	@Transactional
	public List<String> getDiseaseName() {
		return diseaseRepo.findByname();
	}

	@Transactional
	public List<Disease> searchEngine(String text) {
		return diseaseRepo.findByText(text);
	}

}
