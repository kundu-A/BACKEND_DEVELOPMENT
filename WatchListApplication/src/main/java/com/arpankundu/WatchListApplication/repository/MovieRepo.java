package com.arpankundu.WatchListApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arpankundu.WatchListApplication.models.Movie;

@Repository
public interface MovieRepo extends JpaRepository<Movie, Integer>{

}
