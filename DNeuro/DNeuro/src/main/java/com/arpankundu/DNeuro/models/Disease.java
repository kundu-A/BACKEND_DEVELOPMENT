package com.arpankundu.DNeuro.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="Disease_List")
public class Disease {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@NotBlank(message="Disease Name can't be null")
	private String diseaseName;
	@Lob
	@Column(columnDefinition = "TEXT")
	@NotBlank(message="Disease Definition can't be null")
	private String definition;

	@Lob
	@Column(columnDefinition = "TEXT")
	@NotBlank(message="Existing Drug can't be null")
	private String existingDrugs;

	@Lob
	@Column(columnDefinition = "TEXT")
	@NotBlank(message="New Drugs can't be null")
	private String newDurgs;

	@Lob
	@Column(columnDefinition = "TEXT")
	@NotBlank(message="Treatment can't be null")
	private String treatment;

	@Lob
	@Column(columnDefinition = "TEXT")
	@NotBlank(message="Diagnosis can't be null")
	private String diagnosis;

	@Lob
	@Column(columnDefinition = "TEXT")
	@NotBlank(message="New Advances can't be null")
	private String newAdvances;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDiseaseName() {
		return diseaseName;
	}
	public void setDiseaseName(String diseaseName) {
		this.diseaseName = diseaseName;
	}
	public String getExistingDrugs() {
		return existingDrugs;
	}
	public void setExistingDrugs(String existingDrugs) {
		this.existingDrugs = existingDrugs;
	}
	public String getNewDurgs() {
		return newDurgs;
	}
	public void setNewDurgs(String newDurgs) {
		this.newDurgs = newDurgs;
	}
	public String getTreatment() {
		return treatment;
	}
	public void setTreatment(String treatment) {
		this.treatment = treatment;
	}
	public String getNewAdvances() {
		return newAdvances;
	}
	public void setNewAdvances(String newAdvances) {
		this.newAdvances = newAdvances;
	}

	public String getDefinition() {
		return definition;
	}

	public void setDefinition(String definition) {
		this.definition = definition;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public Disease() {
		
	}
	public Disease(Integer id, String diseaseName, String existingDrugs, String newDurgs, String treatment,
			String newAdvances) {
		super();
		this.id = id;
		this.diseaseName = diseaseName;
		this.existingDrugs = existingDrugs;
		this.newDurgs = newDurgs;
		this.treatment = treatment;
		this.newAdvances = newAdvances;
	}
	
}
