package com.bzdgs.dms.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @Author Wcy
 *  用户管理实体类
 */

@Getter
@Setter
@ToString
public class User {

    //用户编号
    private Long id;
    //用户名
    private String username;
    //密码
    private String password;
    //真实姓名
    private String realName;
    //身份证
    private String idCard;
    //所在部门
    private Department department;
    private Long dept_id;
    //入职时间
    private Date hireDate;
    //联系电话
    private String phoneNum;
    //学历
    private Long education_id;
    
    private Education education;
    //现居地址
    private String address;
    //性别
    private int gender;
    //出生日期
    private Date birthDate;
    //备注
    private String comment;
    //状态
    private int state;


}
