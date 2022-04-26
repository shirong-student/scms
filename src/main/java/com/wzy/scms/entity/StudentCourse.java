package com.wzy.scms.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class StudentCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;//主键，自增长
    Integer studentId; // 学生Id
    Integer courseId; // 课程Id
    Date selectDate; // 选课时间
    Float achievement; // 成绩，例如：89.5
}
