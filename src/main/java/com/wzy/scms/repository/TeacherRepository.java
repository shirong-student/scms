package com.wzy.scms.repository;

import com.wzy.scms.entity.Teacher;
import com.wzy.scms.vo.MinMaxAvgAchievementVo;
import com.wzy.scms.vo.TeacherCourseVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

    @Query(value = "select * from teacher where code=?1", nativeQuery = true)
    Teacher selectTeacherByCode(String code);

    @Query("select distinct new com.wzy.scms.vo.TeacherCourseVo(s.code, s.name, c.name, sc.achievement) " +
            " from Teacher t, StudentCourse sc, Course c, Student s " +
            " where ?1=c.teacherId and c.id=sc.courseId and s.id=sc.studentId order by c.name")
    List<TeacherCourseVo> selectStudentCourse(Integer id);

    @Query("select new com.wzy.scms.vo.MinMaxAvgAchievementVo(max(sc.achievement), min(sc.achievement), avg(sc.achievement)) " +
            "from Course c, Teacher t, StudentCourse sc where c.teacherId=?1 and c.id=sc.courseId group by c.name")
    List<MinMaxAvgAchievementVo> selectMinMaxAvg(Integer id);
}
