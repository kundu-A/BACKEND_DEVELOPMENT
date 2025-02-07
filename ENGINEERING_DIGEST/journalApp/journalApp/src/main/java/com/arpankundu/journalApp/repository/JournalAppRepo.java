package com.arpankundu.journalApp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arpankundu.journalApp.models.JournalEntry;
import com.arpankundu.journalApp.models.Users;

@Repository
public interface JournalAppRepo extends JpaRepository<JournalEntry,Integer>{

	List<JournalEntry> findByUsers(Users user);

	Optional<JournalEntry> findByIdAndUsers(Integer id,Users users);

}
