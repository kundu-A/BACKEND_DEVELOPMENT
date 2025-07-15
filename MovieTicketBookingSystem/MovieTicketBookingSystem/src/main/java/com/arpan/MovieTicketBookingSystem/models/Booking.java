package com.arpan.MovieTicketBookingSystem.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="booking_time",nullable = false)
    @NotBlank(message = "Please enter booking time")
    private LocalDateTime bookingTime;

    @Column(name="total_price",nullable = false)
    @NotBlank(message = "Price can't be null")
    private long totalPrice;

}
