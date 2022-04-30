package com.wzy.scms.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SelectedCourseVo {
    String studentCode;
    String studentName;
    String courseCode;
    String courseName;
    Float achievement;
}
