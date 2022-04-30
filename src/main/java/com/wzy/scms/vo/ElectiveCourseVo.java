package com.wzy.scms.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 选修该课程的学生 查询出的数据需要包含课程编号，课程名称，学生学号，姓名，性别，系部，选课时间，成绩
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ElectiveCourseVo {
    String courseCode;
    String courseName;
    String studentCode;
    String studentName;
    String sex;
    String department;
    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    Date selectDate;
    Float achievement;
}
