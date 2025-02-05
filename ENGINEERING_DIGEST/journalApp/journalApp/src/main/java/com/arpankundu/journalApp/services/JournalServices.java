package com.arpankundu.journalApp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arpankundu.journalApp.models.JournalEntry;
import com.arpankundu.journalApp.repository.JournalAppRepo;

import jakarta.transaction.Transactional;

@Service
public class JournalServices {

	@Autowired
	private JournalAppRepo journalAppRepo;

	@Transactional
	public List<JournalEntry> getEntry() {
		return journalAppRepo.findAll();
	}

	@Transactional
	public boolean createEntry(JournalEntry journalEntry) {
		try {
			journalAppRepo.save(journalEntry);
			return true;
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	@Transactional
	public Optional<JournalEntry> getEntryById(Integer id) {
		return journalAppRepo.findById(id);
	}

	@Transactional
	public boolean deleteById(Integer id) {
		try {
			journalAppRepo.deleteById(id);
			return true;
		}catch(Exception e) {
			return false;
		}
	}

	@Transactional
	public boolean updateEntryById(Integer id, JournalEntry journalEntry) {
		JournalEntry element=journalAppRepo.findById(id).get();
		if(element!=null) {
			element.setContent(journalEntry.getContent());
			element.setTitle(journalEntry.getTitle());
			try {
				JournalEntry j=journalAppRepo.save(element);
				if(j!=null)
					return true;
				else
					return false;
			}catch(Exception e) {
				System.out.println(e.getMessage());
				return false;
			}
		}else {
			return false;
		}
	}
}
