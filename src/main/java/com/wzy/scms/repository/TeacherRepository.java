package com.wzy.scms.repository;

import com.wzy.scms.entity.StudentCourse;
import com.wzy.scms.entity.Teacher;
import com.wzy.scms.vo.MinMaxAvgAchievementVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

    @Query("select sc from Teacher t, StudentCourse sc, Course c where ?1=sc.courseId order by sc.courseId")
    List<StudentCourse> selectStudentCourse(Integer id);

    @Query("select new com.wzy.scms.vo.MinMaxAvgAchievementVo(max(sc.achievement), min(sc.achievement), avg(sc.achievement)) " +
            "from Course c, Teacher t, StudentCourse sc where c.teacherId=?1 and c.id=sc.courseId group by c.name")
    List<MinMaxAvgAchievementVo> selectMinMaxAvg(Integer id);
}
