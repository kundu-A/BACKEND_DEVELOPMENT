package com.arpan.MovieTicketBookingSystem.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cast_id")
    private Integer id;

    @Column(name="name",nullable = false)
    @NotBlank(message = "Please mention the cast name")
    private String name;

    @Column(name="role_movie",nullable = false)
    private CastRole role;
}
