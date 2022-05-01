package com.wzy.scms.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@Entity
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id; // 主键，自增长
    @Column(unique = true)
    String code; // 工号
    @NotBlank(message = "用户名不能为空")
    String name; // 姓名
    String sex; // 性别
    @Pattern(regexp = "^(((13\\d)|(14[579])|(15([0-3]|[5-9]))|(166)|(17[0135678])|(18\\d)|(19[89]))\\d{8})$", message = "手机号格式不合法")
    String phone; // 电话
    String password; // 密码
    String department; // 系部
}
