package com.arpankundu.DNeuro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.arpankundu.DNeuro.models.Disease;

@Repository
public interface DiseaseRepo extends JpaRepository<Disease, Integer>{

	@Query(value="SELECT d.disease_name FROM disease_list d" , nativeQuery=true)
	List<String> findByname();

	@Query(value = "SELECT * FROM disease_list d WHERE d.disease_name LIKE %:text% OR d.existing_drugs LIKE %:text% OR d.new_durgs LIKE %:text OR d.new_advances LIKE %:text% OR d.treatment LIKE %:text%" , nativeQuery=true)
	List<Disease> findByText(@Param(value = "text") String text);

	@Query(value="SELECT * FROM disease_list d WHERE d.disease_name LIKE :text%",nativeQuery=true)
	List<Disease> findByAlphabate(@Param(value="text")String text);

}
