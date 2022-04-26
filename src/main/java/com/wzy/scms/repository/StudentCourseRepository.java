package com.wzy.scms.repository;

import com.wzy.scms.entity.StudentCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentCourseRepository extends JpaRepository<StudentCourse, Integer> {

}
