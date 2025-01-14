package com.arpankundu.DNeuro.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.arpankundu.DNeuro.models.UserPrinciple;
import com.arpankundu.DNeuro.models.Users;
import com.arpankundu.DNeuro.repository.UserRepo;

@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users users=userRepo.findByUsername(username);
		if(users==null) {
			System.out.println("User not found!!");
			throw new UsernameNotFoundException("User not found!!"); 
		}
		return new UserPrinciple(users);
	}

}
