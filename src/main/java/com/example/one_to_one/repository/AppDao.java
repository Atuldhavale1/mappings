package com.example.one_to_one.repository;

import com.example.one_to_one.entity.Instructor;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class AppDao {

    private final EntityManager entityManager;

    @Transactional
    public Instructor saveInstructor(Instructor instructor) {
        return entityManager.merge(instructor);
    }
}
