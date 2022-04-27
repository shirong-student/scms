package com.wzy.scms.controller;

import com.wzy.scms.entity.Course;
import com.wzy.scms.repository.CourseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

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
    @PostMapping("/delete")
    public String delete(Integer id) {
        for (int i = 0; i < courseRepository.getCourseId().size(); i++) {
            if (Objects.equals(courseRepository.getCourseId().get(i), id)) {
                return "Delete Failed";
            }
        }
        courseRepository.deleteById(id);
        return "Delete Successfully";
    }

    /**
     * 课程的修改，根据id，修改课程的数据。
     */
    @PostMapping("/update")
    public String update(Integer id, @RequestBody Course course) {
        course.setId(id);
        courseRepository.save(course);
        return "Update Successfully";
    }

    /**
     * 实现查询所有课程的接口,分页查询并按课程编号升序排序。（学生选课时使用）
     */
    @PostMapping("/select-all-course")
    public Page<Course> selectCourse(Integer page) {
        Pageable pageable = PageRequest.of(page, 10, Sort.Direction.DESC, "id");
        return courseRepository.findAll(pageable);
    }

    /**
     * 实现根据课程名称模糊查询课程的接口，分页查询并按编号升序排序。（学生选课时使用）
     */
    @PostMapping("/select-course")
    public Page<Course> selectCourse(Integer page, String searchVal) {
        Pageable pageable = PageRequest.of(page, 2, Sort.Direction.DESC, "code");
        return courseRepository.selectCourse(searchVal, pageable);
    }
}
