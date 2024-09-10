package com.arpan.StudentData.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arpan.StudentData.models.Students;

@Repository
public interface StudentRepo extends JpaRepository<Students, Integer>{

	Optional<Students> findById(int id);
}
