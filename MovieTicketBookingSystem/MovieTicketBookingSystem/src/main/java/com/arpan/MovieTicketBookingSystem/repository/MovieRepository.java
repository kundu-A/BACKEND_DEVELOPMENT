package com.arpan.MovieTicketBookingSystem.repository;

import com.arpan.MovieTicketBookingSystem.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Integer> {
}
