package com.example.one_to_one.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class InstructorDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /*
     * ALTER TABLE instructor_details AUTO_INCREMENT = 1001;
     * */
    private int instructorDetailId;

    private String hobby;

    private String qualification;
}
