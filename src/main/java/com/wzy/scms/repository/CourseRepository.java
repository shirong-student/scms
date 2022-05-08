package com.wzy.scms.repository;

import com.wzy.scms.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

    @Query("from Course where code=?1")
    Course getCode(String code);

    @Query(value = "select course_id from student_course where student_id=?1", nativeQuery = true)
    List<Integer> getCourseId(Integer id);

    @Query(value = "SELECT c FROM Course c WHERE c.name LIKE %?1%")
    Page<Course> selectCourse(String searchVal, Pageable pageable);

    @Query(value = "select id from Course where code=?1", nativeQuery = true)
    Integer getIDbyCode(String code);
}
