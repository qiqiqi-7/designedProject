package cn.edu.hbpu.education.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author 一眼万年
 * @since 2021-05-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 系统使用者的id编号
     */
    private Integer userId;

    /**
     * 外键，关联学生表或者教师表
     */
    private String userCode;

    /**
     * 系统使用者的真实姓名
     */
    private String userName;

    /**
     * 系统使用者的出生日期
     */
    private String userBirthdate;

    /**
     * 系统使用者的家庭住址
     */
    private String userLocation;

    /**
     * 系统使用者的手机
     */
    private String userPhone;

    /**
     * 系统使用者的性别
     */
    private String userSex;

    /**
     * 系统使用者的邮箱
     */
    private String userEmail;

    /**
     * 系统使用者的类型(学生用户还是教师用户)
     */
    private String userType;


    private transient String vericode;


}
