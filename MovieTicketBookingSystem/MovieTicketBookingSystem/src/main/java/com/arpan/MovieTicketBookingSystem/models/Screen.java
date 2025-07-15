package com.arpan.MovieTicketBookingSystem.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Screen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="screen_id")
    private Integer id;

    @Column(name="screen_name",nullable = false)
    private String screenName;

    @Column(name="total_seats",nullable = false)
    private int totalSeats;

}
