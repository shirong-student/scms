package com.wzy.scms.repository;

import com.wzy.scms.entity.Course;
import com.wzy.scms.entity.StudentCourse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

    @Query("from Course where code=?1")
    Course getCode(String code);

    @Query(value = "select course_id from student_course", nativeQuery = true)
    List<Integer> getCourseId();

    @Query(value = "SELECT c FROM Course c WHERE c.name LIKE %?1%")
    Page<Course> selectCourse(String searchVal, Pageable pageable);
}
