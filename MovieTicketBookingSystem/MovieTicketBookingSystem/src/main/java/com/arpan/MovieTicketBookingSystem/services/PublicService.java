package com.arpan.MovieTicketBookingSystem.services;

import com.arpan.MovieTicketBookingSystem.DTO.UserDTO;
import com.arpan.MovieTicketBookingSystem.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublicService {

    @Autowired
    UserRepo userRepo;

    public UserDTO register(UserDTO user) {
        return user;
    }

    public boolean verify(UserDTO user) {
        return true;
    }
}
