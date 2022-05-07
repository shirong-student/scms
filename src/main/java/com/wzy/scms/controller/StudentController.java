package com.wzy.scms.controller;

import com.wzy.scms.entity.Student;
import com.wzy.scms.repository.StudentCourseRepository;
import com.wzy.scms.repository.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {
    final StudentRepository studentRepository;
    final StudentCourseRepository studentCourseRepository;

    public StudentController(StudentRepository studentRepository, StudentCourseRepository studentCourseRepository) {
        this.studentRepository = studentRepository;
        this.studentCourseRepository = studentCourseRepository;
    }

    /**
     * list
     */
    @GetMapping("/list")
    public Page<Student> list(Integer page) {
        return studentRepository.findAll(PageRequest.of(page, 6, Sort.by(Sort.Direction.ASC, "code")));
    }

    /**
     * 学生注册：增加。code不能重复。
     */
    @PostMapping("/add")
    public String add(@RequestBody Student student) {
        if (studentRepository.getCode(student.getCode()) != null || studentRepository.getName(student.getName()) != null) {
            return "Add Failed";
        }
        studentRepository.save(student);
        return "Add Successfully";
    }

    /**
     * 学生删除：根据id删除学生，有选课的学生，必须把选课记录删除。
     */
    @PostMapping("/delete")
    public String delete(Integer id) {
        if (studentRepository.findById(id).isPresent()) {
            if (studentCourseRepository.findById(id).isPresent()) {
                studentCourseRepository.deleteById(id);
            }
            studentRepository.deleteById(id);
            return "Delete Successfully";
        } else {
            return "Delete Failed";
        }
    }

    /**
     * 学生修改个人信息：修改，根据id，修改学生的数据。
     */
    @PostMapping("/update")
    public String update(@RequestBody Student student) {
        studentRepository.save(student);
        return "Update Successfully";
    }

    /**
     * 实现根据姓名和密码的登录接口
     */
    @GetMapping("/login-name-password")
    public String loginNamePassword(String name, String password) {
        if (studentRepository.getNamePassword(name, password) == null) {
            return "Login Failed";
        }
        return "Login successfully";
    }

    /**
     * 实现根据id查询学生信息的接口
     */
    @GetMapping("/id-list")
    public Student idList(Integer id) {
        return studentRepository.getId(id);
    }

    /**
     * 实现根据code查询学生的接口
     */
    @GetMapping("/code-list")
    public Student codeList(String code) {
        return studentRepository.getCode(code);
    }
}
