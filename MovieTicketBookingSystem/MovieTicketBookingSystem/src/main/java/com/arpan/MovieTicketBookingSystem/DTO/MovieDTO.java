package com.arpan.MovieTicketBookingSystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieDTO {

    private String title;
    private String description;
    private String duration;
    private LocalDateTime releaseDate;
    private String posterUrl;
    private String language;

}
