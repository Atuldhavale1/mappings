package com.example.one_to_one.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int instructorId;

    private String firstName;

    private String lastName;

    private String phone;

    @OneToOne()
    @JoinColumn(name = "instructor_detail_id")
    private InstructorDetail instructorDetail;
}
