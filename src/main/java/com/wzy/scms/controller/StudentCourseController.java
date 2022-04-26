package com.wzy.scms.controller;

import com.wzy.scms.repository.StudentCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student-course")
public class StudentCourseController {
    @Autowired
    StudentCourseRepository studentCourseRepository;

    @PostMapping("/isId")
    public boolean isId(Integer id) {
        return studentCourseRepository.findById(id).isEmpty();
    }
}
