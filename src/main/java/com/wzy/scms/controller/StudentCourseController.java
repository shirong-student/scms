package com.wzy.scms.controller;

import com.wzy.scms.entity.StudentCourse;
import com.wzy.scms.repository.CourseRepository;
import com.wzy.scms.repository.StudentCourseRepository;
import com.wzy.scms.repository.StudentRepository;
import com.wzy.scms.vo.AvgVo;
import com.wzy.scms.vo.ElectiveCourseVo;
import com.wzy.scms.vo.MinMaxAvgAchievementVo;
import com.wzy.scms.vo.SelectedCourseVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/student-course")
public class StudentCourseController {
    final
    StudentCourseRepository studentCourseRepository;
    final
    StudentRepository studentRepository;
    final
    CourseRepository courseRepository;

    public StudentCourseController(StudentCourseRepository studentCourseRepository, StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentCourseRepository = studentCourseRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    /**
     * 学生选课：根据学号和课程号，实现添加选课的接口，如果学生已经选过此课程，则不能添加。（学生进行选课使用）
     */
    @PostMapping("/add")
    public String add(String studentCode, String courseCode, @RequestBody StudentCourse studentCourse) {
        if (studentCourseRepository.selectStudentCourse(studentCode, courseCode).size() == 0) {
            studentCourseRepository.save(studentCourse);
            return "Add Successfully";
        }
        return "Add Failed";
    }

    /**
     * 学生退选：根据学号和课程号，实现退选的接口。（学生进行退选使用）
     */
    @PostMapping("/delete")
    public String delete(String studentCode, String courseCode, @RequestBody StudentCourse studentCourse) {
        if (studentCourseRepository.selectStudentCourse(studentCode, courseCode).size() != 0) {
            studentCourseRepository.deleteById(studentCourse.getId());
            return "delete Successfully";
        }
        return "delete Failed";
    }

    /**
     * 学生退学：根据学号，实现删除该学生的选课记录的接口。（删除学生时使用）
     */
    @PostMapping("/delete-id")
    public String deleteId(Integer id) {
        if (studentCourseRepository.findById(id).isPresent()) {
            studentCourseRepository.deleteById(id);
            return "delete Successfully";
        }
        return "delete Failed";
    }

    /**
     * 教师成绩评定：根据学号和课程号，修改学生的成绩。（教师进行成绩评定使用）
     */
    @PostMapping("/update")
    public String update(String studentCode, String courseCode, @RequestBody StudentCourse studentCourse) {
        if (studentCourseRepository.selectStudentCourse(studentCode, courseCode).size() != 0) {
            studentCourseRepository.save(studentCourse);
            return "update Successfully";
        }
        return "update Failed";
    }

    /**
     * 实现根据学号查询学生所选课程的接口（查询出的数据需要包含学号，姓名，课程编号，课程名，成绩），按成绩升序排序输出。
     */
    @PostMapping("/select-id")
    public List<SelectedCourseVo> selectId(String code) {
        return studentCourseRepository.selectStudentCourseByCode(code);
    }

    /**
     * 实现根据学号，统计查询该生所选课程的平均分的接口
     */
    @PostMapping("/select-avg-achievement")
    public List<AvgVo> selectAvgAchievement(String code) {
        return studentCourseRepository.selectAvgAchievement(code);
    }

    /**
     * 实现根据课程名称查询选修该课程的学生的接口，（查询出的数据需要包含课程编号，课程名称，学生学号，
     * 姓名，性别，系部，选课时间，成绩），分页查询并按学生编号升序排序输出。
     */
    @PostMapping("/select-course-student")
    public List<ElectiveCourseVo> selectCourseStudent(String name){
//        Pageable pageable = PageRequest.of(page, 10, Sort.Direction.ASC);
        return studentCourseRepository.selectCourseStudent(name);
    }

    /**
     * 实现根据课程名称，统计查询课程的最高分、最低分、平均分的接口
     */
    @PostMapping("/select-min-max-avg")
    public List<MinMaxAvgAchievementVo> selectMinMaxAvg(String name) {
        return studentCourseRepository.selectMinMaxAvg(name);
    }
}
