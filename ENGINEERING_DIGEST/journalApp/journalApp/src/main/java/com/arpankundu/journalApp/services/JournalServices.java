package com.arpankundu.journalApp.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arpankundu.journalApp.models.JournalEntry;
import com.arpankundu.journalApp.models.Users;
import com.arpankundu.journalApp.repository.JournalAppRepo;

import jakarta.transaction.Transactional;

@Service
public class JournalServices {

	@Autowired
	private JournalAppRepo journalAppRepo;

	public List<JournalEntry> getEntry(Users user) {
		return journalAppRepo.findByUsers(user);
	}

	public boolean createEntry(JournalEntry journalEntry,Users user) {
		try {
			journalEntry.setUsers(user);
			journalEntry.setSharedBy(user.getName());
			journalEntry.setDate(LocalDate.now());
			journalAppRepo.save(journalEntry);
			return true;
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public Optional<JournalEntry> getEntryById(Integer id,Users user) {
		return journalAppRepo.findByIdAndUsers(id,user);
	}

	public boolean deleteById(Integer id,Users user) {
		try {
			Optional<JournalEntry> entry = journalAppRepo.findByIdAndUsers(id, user);
			if(entry.isPresent()) {
				journalAppRepo.deleteById(id);
				return true;
			}
			else
				return false;
		}catch(Exception e) {
			return false;
		}
	}

	public boolean updateEntryById(Integer id, JournalEntry journalEntry,Users user) {
		JournalEntry element=journalAppRepo.findByIdAndUsers(id,user).get();
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
