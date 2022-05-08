package com.wzy.scms.controller;

import com.wzy.scms.entity.Course;
import com.wzy.scms.repository.CourseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course")
public class CourseController {

    final
    CourseRepository courseRepository;

    public CourseController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    /**
     * 课程的增加。code不能重复
     */
    @PostMapping("/add")
    public String add(@RequestBody Course course) {
        if (courseRepository.getCode(course.getCode()) != null) {
            return "Add Failed";
        }
        courseRepository.save(course);
        return "Add Successfully";
    }

    /**
     * 课程的删除，根据id删除课程，有被学生选修的课程，不能删除
     */
    @GetMapping("/delete")
    public String delete(Integer id) {
        if (courseRepository.getCourseId(id).size() > 0) {
            return "Delete Failed";
        }
        courseRepository.deleteById(id);
        return "Delete Successfully";
    }

    /**
     * 课程的修改，根据id，修改课程的数据。
     */
    @PostMapping("/update")
    public String update(@RequestBody Course course) {
        courseRepository.save(course);
        return "Update Successfully";
    }

    /**
     * 实现查询所有课程的接口,分页查询并按课程编号升序排序。（学生选课时使用）
     */
    @GetMapping("/list")
    public Page<Course> selectCourse(Integer page) {
        Pageable pageable = PageRequest.of(page, 10, Sort.Direction.ASC, "code");
        return courseRepository.findAll(pageable);
    }

    /**
     * 实现根据课程名称模糊查询课程的接口，分页查询并按编号升序排序。（学生选课时使用）
     */
    @GetMapping("/list-name")
    public Page<Course> selectCourse(@RequestParam(value = "page", defaultValue = "0") Integer page, String searchVal) {
        Pageable pageable = PageRequest.of(page, 2, Sort.Direction.ASC, "code");
        return courseRepository.selectCourse(searchVal, pageable);
    }
}
