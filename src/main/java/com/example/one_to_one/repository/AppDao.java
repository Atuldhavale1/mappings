package com.example.one_to_one.repository;

import com.example.one_to_one.entity.Instructor;

import com.example.one_to_one.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class AppDao {

    private final EntityManager entityManager;

    @Transactional
    public Instructor saveInstructor(Instructor instructor) {
        return entityManager.merge(instructor);
    }

    @Transactional
    public InstructorDetail saveInstructorDetail(InstructorDetail instructorDetail) {
        return  entityManager.merge(instructorDetail);
    }
    public Instructor getInstructorById(int id) {
        return entityManager.find(Instructor.class, id);
    }

    @Transactional
    public String deleteInstructorById(int instructorId) {
        Instructor instructor = getInstructorById(instructorId);
        if (instructor != null) {
            entityManager.remove(instructor);
//            entityManager.remove(instructor.getInstructorDetail());
            return "Deleted successfully";
        } else {
            return "Not Found!!";
        }

    }

    public List<Instructor> getAllInstructors() {
        List<Instructor> instructors = entityManager.createQuery("from Instructor").getResultList();

        return instructors;
    }

//    @Transactional
//    public Instructor updateInstructorById(int instructorId, Instructor instructor) {
//
//        Instructor instructor1 = getInstructorById(instructorId);
//        InstructorDetail instructorDetail = instructor.getInstructorDetail();
//        instructorDetail.setInstructorDetailId(instructor1.getInstructorDetail().getInstructorDetailId());
//        if(instructor1 != null) {
//            InstructorDetail updaInstructorDetail =  entityManager.merge(instructorDetail);
//
//            instructor.setInstructorDetail(updaInstructorDetail);
//            Instructor updatedInstructor = entityManager.merge(instructor);
//
//            updatedInstructor.setInstructorDetail(updaInstructorDetail);
//
//            return updatedInstructor;
//
//        }else{
//            return null;
//        }
//
//    }
public InstructorDetail findInstructorDetailById(int instructorDetailId) {

    return entityManager.find(InstructorDetail.class, instructorDetailId);
}
    @Transactional
    public String deleteInstructorDetailById(int instructorDetailId) {

        InstructorDetail instructorDetail = entityManager.find(InstructorDetail.class, instructorDetailId);
        if (instructorDetail != null) {
            Instructor instructor = instructorDetail.getInstructor();
            instructor.setInstructorDetail(null);
            entityManager.remove(instructorDetail);
            return "Deleted successfully";
        } else {
            return "Not Found!!";
        }
    }

    @Transactional
    public Instructor updateInstrutorDetail(int instructorId, InstructorDetail instructorDetail) {

        Instructor instructor = entityManager.find(Instructor.class, instructorId);
        if(instructor != null) {
            instructor.setInstructorDetail(instructorDetail);
            return  entityManager.merge(instructor);
        }else{
            return  null;
        }
    }

}


