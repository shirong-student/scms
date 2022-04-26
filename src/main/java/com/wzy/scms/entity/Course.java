package com.wzy.scms.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id; // 主键，自增长
    String code; // 课程编号
    String name; // 课程名称
    Float credit; // 学分，例如：3.5学分
}
