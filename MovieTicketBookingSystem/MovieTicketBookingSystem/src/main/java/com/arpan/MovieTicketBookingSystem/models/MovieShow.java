package com.arpan.MovieTicketBookingSystem.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieShow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="show_id",nullable = false)
    private Integer id;

    @Column(name="show_time",nullable = false)
    private LocalTime showTime;

    @Column(name="show_date",nullable = false)
    private LocalDate showDate;

}
