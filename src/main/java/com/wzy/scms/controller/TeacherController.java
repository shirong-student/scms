package com.wzy.scms.controller;

import com.wzy.scms.entity.Teacher;
import com.wzy.scms.repository.TeacherRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    final
    TeacherRepository teacherRepository;

    public TeacherController(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    /**
     * 教师注册
     */
    @PostMapping("/add")
    public String add(@RequestBody Teacher teacher) {
        teacherRepository.save(teacher);
        return "Add Successfully";
    }

    /**
     * 教师修改自己的信息
     */
    @PostMapping("/update")
    public String update(@RequestBody Teacher teacher) {
        teacherRepository.save(teacher);
        return "update Successfully";
    }

    /**
     * 教师删除
     */
    @PostMapping("/delete")
    public String delete(Integer id) {
        teacherRepository.deleteById(id);
        return "delete Successfully";
    }

    /**
     * 教师能查询自己单门课程的选课信息
     */
//    @PostMapping("/list")
//    public String list(Integer id){
//        teacherRepository.deleteById(id);
//        return "delete Successfully";
//    }
}
