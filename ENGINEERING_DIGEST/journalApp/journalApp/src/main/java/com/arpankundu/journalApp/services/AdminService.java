package com.arpankundu.journalApp.services;

import java.util.List;

import com.arpankundu.journalApp.models.JournalEntry;
import com.arpankundu.journalApp.models.JournalResponse;
import com.arpankundu.journalApp.repository.JournalAppRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.arpankundu.journalApp.exceptionHandler.EmailNotVerifiedException;
import com.arpankundu.journalApp.exceptionHandler.UserAlreadyExistsException;
import com.arpankundu.journalApp.models.Role;
import com.arpankundu.journalApp.models.Users;
import com.arpankundu.journalApp.repository.UserRepo;

@Service
public class AdminService {

	@Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepo userRepo;

    @Autowired
    UtilityService utilityService;
    
    @Autowired
    MailOTPService mailOTPService;
    
    @Autowired
    EmailService emailService;

    @Autowired
    JournalAppRepo journalAppRepo;

    public Users registerAdmin(Users user) {
    	String email = user.getEmail();
        if (!mailOTPService.verifiedEmails.contains(email)) 
            throw new EmailNotVerifiedException("Email verification required.");

        if (userRepo.findUsersByEmail(email) != null)
            throw new UserAlreadyExistsException("Admin with this email already exists.");

        user.setUsername(utilityService.extractUsernameFromEmail(user));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.ROLE_ADMIN);
        try {
        	Users users=userRepo.save(user);
        	emailService.welcomeEmail(email);
        	return users;
        }catch(Exception e) {
        	throw new RuntimeException("User registration failed", e); 
        }
    }

	public List<?> getAllUserDetails() {
		return userRepo.findAll();
	}

    public JournalResponse getAllNotes(Integer pageNumber , Integer pageSize, String sortBy,String sortDir) {

        Sort sort=null;
        if(sortDir.equalsIgnoreCase("asc"))
            sort=Sort.by(sortBy).ascending();
        else
            sort=Sort.by(sortBy).descending();

        Pageable p= PageRequest.of(pageNumber,pageSize, sort);
        Page<JournalEntry> pagePost=journalAppRepo.findAll(p);
        List<JournalEntry> allPost=pagePost.getContent();

        JournalResponse journalResponse=new JournalResponse();

        journalResponse.setContent(allPost);
        journalResponse.setPageNumber(pagePost.getNumber());
        journalResponse.setPageSize(pagePost.getSize());
        journalResponse.setTotalElements(pagePost.getTotalElements());
        journalResponse.setTotalPages(pagePost.getTotalPages());
        journalResponse.setLastPage(pagePost.isLast());

        return  journalResponse;
    }
}
