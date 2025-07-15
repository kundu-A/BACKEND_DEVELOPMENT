package com.arpan.MovieTicketBookingSystem.services;

import com.arpan.MovieTicketBookingSystem.DTO.MovieDTO;
import com.arpan.MovieTicketBookingSystem.DTO.UserDTO;
import com.arpan.MovieTicketBookingSystem.models.Movie;
import com.arpan.MovieTicketBookingSystem.models.Users;
import org.springframework.stereotype.Service;

@Service
public class DTOService {

    public UserDTO convertUserToUserDTO(Users users){
        return new UserDTO();
    }

    public Users convertUserDTOToUsers(UserDTO userDTO){
        return new Users();
    }

    public MovieDTO convertMovieToMovieDTO(Movie movie){
        return new MovieDTO();
    }

    public Movie convertMovieDTOToMovie(MovieDTO movieDTO){
        return new Movie();
    }
}
