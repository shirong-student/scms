package com.wzy.scms.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
public class Student implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id; // 主键，自增长
    @Column(unique = true)
    String code; // 学号
//    @NotBlank(message = "用户名不能为空")
    String name; // 姓名
    String sex; // 性别
    @Past(message = "出生日期不合法")
    @JsonFormat(pattern = "yyyy-MM-dd HH-mm-ss")
    Date birthday; // 出生日期
    @Email(message = "邮箱地址不合法")
    String email; // 邮箱
    @Pattern(regexp = "^(((13\\d)|(14[579])|(15([0-3]|[5-9]))|(166)|(17[0135678])|(18\\d)|(19[89]))\\d{8})$", message = "手机号格式不合法")
    String phone; // 电话
    String password; // 密码
    String department; // 系部
}
