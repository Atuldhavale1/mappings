package com.example.one_to_one.controller;

import com.example.one_to_one.entity.Instructor;
import com.example.one_to_one.entity.InstructorDetail;
import com.example.one_to_one.repository.AppDao;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class DemoController {
    private final AppDao appDao;

    @PostMapping("/save")
    public Instructor saveInstructor(@RequestBody Instructor instructor) {
        InstructorDetail instructorDetail = instructor.getInstructorDetail();
        instructorDetail = appDao.saveInstructorDetail(instructorDetail); // 1002

        instructor.setInstructorDetail(instructorDetail);

        return appDao.saveInstructor(instructor);
    }
    @GetMapping("/getbyid/{instructorId}")
    public Instructor getInstructorById(@PathVariable int instructorId) {
        return appDao.getInstructorById(instructorId);
    }
    @GetMapping("/getall")
    public List<Instructor> getAllInstructors() {
        return appDao.getAllInstructors();
    }

    @DeleteMapping("/deletebyid/{instructorId}")
    public String deleteByInstructorId(@PathVariable int instructorId) {
        String message = appDao.deleteInstructorById(instructorId);

        return message;

    }

    @PutMapping("/updatebyid/{instructorId}")
    public Instructor updateByInstructorId(@PathVariable int instructorId, @RequestBody Instructor instructor) {
        Instructor savedInstructor = appDao.getInstructorById(instructorId);
        if (savedInstructor != null) {
            InstructorDetail updatedInstructorDetail = instructor.getInstructorDetail();
            updatedInstructorDetail.setInstructorDetailId(savedInstructor.getInstructorDetail().getInstructorDetailId());


            appDao.saveInstructorDetail(updatedInstructorDetail);

            instructor.setInstructorId(savedInstructor.getInstructorId());
            instructor.setInstructorDetail(updatedInstructorDetail);

            return appDao.saveInstructor(instructor);


        } else {
            return null;
        }
    }
}
