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
public class TStudent implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 学生表的id编号,uuid生成
     */
    private String stuId;

    /**
     * 学生表的账户名称
     */
    private String stuAccount;

    /**
     * 学生表的账户密码
     */
    private String stuPwsd;

    /**
     * 学生账户的头像
     */
    private String stuHeadicon;

    /**
     * 学生账户的状态
     */
    private String stuStatus;

    /**
     * 学生表账户注册时间
     */
    private String stuRegtime;

}
