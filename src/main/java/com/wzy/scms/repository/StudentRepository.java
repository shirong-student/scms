package com.wzy.scms.repository;

import com.wzy.scms.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Query("from Student where code=?1")
    Student getCode(String code);

    @Query("from Student where name=?1")
    Student getName(String name);

    @Query("from Student where name=?1 and password=?2")
    Student getNamePassword(String name, String password);

}
