package com.wzy.scms.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id; // 主键，自增长
    @Column(unique = true)
    String code; // 学号
    String name; // 姓名
    String sex; // 性别
    Date birthday; // 出生日期
    String email; // 邮箱
    String phone; // 电话
    String password; // 密码
    String department; // 系部
}
