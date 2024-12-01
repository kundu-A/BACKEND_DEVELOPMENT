package com.arpankundu.DNeuro.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="Disease_List")
public class Disease {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@NotBlank(message="Disease Name can't be null")
	private String diseaseName;
	@NotBlank(message="Exising Drug can't be null")
	private String existingDrugs;
	@NotBlank(message="New Drugs can't be null")
	private String newDurgs;
	@NotBlank(message="Treatment can't be null")
	private String treatment;
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
