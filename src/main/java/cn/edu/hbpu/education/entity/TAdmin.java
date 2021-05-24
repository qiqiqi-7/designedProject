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
public class TAdmin implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 管理员账号id编号uuid
     */
    private String adminId;

    /**
     * 管理员账号账号名称
     */
    private String adminName;

    /**
     * 管理员账号密码
     */
    private String adminPwsd;

    /**
     * 管理员账号类型，权限
     */
    private String adminType;

    /**
     * 管理员账号的管理账号
     */
    private String adminCode;

    /**
     * 管理员账号的创建时间
     */
    private String adminRegtime;

    /**
     * 管理员账号的状态
     */
    private String adminStatus;


}
