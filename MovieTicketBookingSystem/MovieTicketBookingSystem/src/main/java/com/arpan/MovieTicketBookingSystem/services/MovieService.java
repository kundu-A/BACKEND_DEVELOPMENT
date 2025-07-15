package com.arpan.MovieTicketBookingSystem.services;

import com.arpan.MovieTicketBookingSystem.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;
}
