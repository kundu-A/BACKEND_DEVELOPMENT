package com.arpan.login.OTPLogin.service;

import com.arpan.login.OTPLogin.models.UserPrinciple;
import com.arpan.login.OTPLogin.models.Users;
import com.arpan.login.OTPLogin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserServiceDetails implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    public UserDetails loadUserByPhoneNumber(String phoneNumber){
        Users users=userRepository.findUsersByPhoneNumber(phoneNumber);
        if(users==null){
            System.out.println("User not found");
            throw new UsernameNotFoundException("User not found");
        }
        return new UserPrinciple(users);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
