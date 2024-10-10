package com.example.one_to_one.controller;

import com.example.one_to_one.entity.Instructor;
import com.example.one_to_one.entity.InstructorDetail;
import com.example.one_to_one.repository.AppDao;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
