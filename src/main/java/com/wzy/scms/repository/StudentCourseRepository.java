package com.wzy.scms.repository;

import com.wzy.scms.entity.StudentCourse;
import com.wzy.scms.vo.AvgVo;
import com.wzy.scms.vo.ElectiveCourseVo;
import com.wzy.scms.vo.MinMaxAvgAchievementVo;
import com.wzy.scms.vo.SelectedCourseVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StudentCourseRepository extends JpaRepository<StudentCourse, Integer> {

    @Query(value = "select sc.* from student_course sc, student s, course c " +
            " where (select student.id from student where student.code = ?1) = sc.student_id and " +
            " (select course.id from course where course.code = ?2) = sc.course_id", nativeQuery = true)
    StudentCourse selectStudentCourse(String studentCode, String courseCode);

    @Transactional
    @Modifying
    @Query(value = "delete from student_course where student_id=?1", nativeQuery = true)
    int deleteStudentId(Integer studentId);

    //实现根据学号查询学生所选课程的接口（查询出的数据需要包含学号，姓名，课程编号，课程名，成绩），按成绩升序排序输出。
    @Query("select distinct new com.wzy.scms.vo.SelectedCourseVo(s.code, s.name, c.code, c.name, sc.achievement) " +
            " from Student s, Course c , StudentCourse sc" +
            " where s.code = ?1 and s.id = sc.studentId and c.id = sc.courseId order by sc.achievement asc")
    List<SelectedCourseVo> selectStudentCourseByCode(String code);

    /**
     * -- 实现根据学号，统计查询该生所选课程的平均分的接口
     * SELECT
     * c.`name`,
     * AVG( sc.achievement )
     * FROM
     * student_course sc,
     * course c
     * WHERE
     * sc.course_id IN (
     * SELECT
     * sc.course_id
     * FROM
     * student_course sc
     * WHERE
     * sc.student_id = ( SELECT s.id FROM student s WHERE s.CODE = "1001" ))
     * ORDER BY
     * c.id
     */
    @Query("select new com.wzy.scms.vo.AvgVo(c.name,AVG(sc.achievement)) from StudentCourse sc ,Course c " +
            " where sc.courseId in (SELECT sc.courseId FROM StudentCourse sc WHERE sc.studentId = " +
            " ( SELECT s.id FROM Student s WHERE s.code = ?1 )) GROUP BY sc.courseId")
    List<AvgVo> selectAvgAchievement(String code);

    /**
     * 实现根据课程名称查询选修该课程的学生的接口，（查询出的数据需要包含课程编号，课程名称，学生学号，
     * 姓名，性别，系部，选课时间，成绩），分页查询并按学生编号升序排序输出。
     * SELECT
     * c.`code`,
     * c.`name`,
     * s.`code`,
     * s.`name`,
     * s.sex,
     * s.department,
     * sc.select_date,
     * sc.achievement
     * FROM
     * student s,
     * course c,
     * student_course sc
     * WHERE
     * c.`name` = "tom11"
     * AND c.id = sc.course_id
     * AND s.id = sc.student_id
     */
    @Query("select new com.wzy.scms.vo.ElectiveCourseVo(c.code,c.name,s.code,s.name,s.sex,s.department,sc.selectDate,sc.achievement) " +
            " from Student s, Course c, StudentCourse sc " +
            " where c.id=sc.courseId and s.id=sc.studentId and  c.name=?1")
    Page<ElectiveCourseVo> selectCourseStudent(String name, Pageable pageable);

    /**
     * 实现根据课程名称，统计查询课程的最高分、最低分、平均分的接口
     * SELECT
     * MAX( sc.achievement ) AS maxAchievement,
     * MIN( sc.achievement ) AS minAchievement,
     * AVG( sc.achievement ) AS avgAchievement
     * FROM
     * course c,
     * student_course sc
     * WHERE
     * c.`name` = "tom11"
     * AND c.id = sc.course_id
     * GROUP BY
     * c.`name`
     */
    @Query("select new com.wzy.scms.vo.MinMaxAvgAchievementVo(MAX( sc.achievement ), MIN( sc.achievement ),AVG( sc.achievement )) " +
            " from Course c, StudentCourse sc where c.name=?1 and c.id=sc.courseId group by c.name")
    List<MinMaxAvgAchievementVo> selectMinMaxAvg(String name);
}
