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
public class TTeachaccount implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * uuid自动生成的主键
     */
    private String teachId;

    /**
     * 教师的账户名称
     */
    private String teachAccount;

    /**
     * 教师的账户密码
     */
    private String teachPwsd;

    /**
     * 教师的账户头像
     */
    private String teachHeadicon;

    /**
     * 教师的学历水平
     */
    private String teachGrade;

    /**
     * 教师的专业
     */
    private String teachMajor;

    /**
     * 0代表非专业教师，1代表专业教师
     */
    private String teachType;

    /**
     * 教师的简历
     */
    private String teachIntroduce;

    /**
     * 教师的信誉等级
     */
    private Integer teachGracredit;

    /**
     * 教师的账户状态
     */
    private String teachAccstatus;

    /**
     * 教师账户的注册时间
     */
    private String teachRegtime;

    /**
     * 教师账户对应的教师资历证明
     */
    private String teachFile;

    /**
     * 教师账户对应的教师学历证明
     */
    private String teachLectfile;

    /**
     * 教师账户对应的教龄
     */
    private String teachExperience;

    private transient TUser tUser;

    private transient TAbility tAbility;
}
