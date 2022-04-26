package com.wzy.scms.controller;

import com.wzy.scms.entity.Student;
import com.wzy.scms.repository.StudentRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {
    final
    StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    /**
     * 学生的增加。code不能重复, 用户名不能重复。
     */
    @PostMapping("/add")
    public void add(@RequestBody Student student) {
        studentRepository.save(student);
    }
}
