package com.arpan.MovieTicketBookingSystem.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="seat_id",nullable = false)
    private Integer id;

    @Column(name="seat_number",nullable = false)
    private int seatNumber;

    @Column(name="seat_type",nullable = false)
    private String seatType;

    @Column(name="is_booked",nullable = false)
    private boolean isBooked;

}
