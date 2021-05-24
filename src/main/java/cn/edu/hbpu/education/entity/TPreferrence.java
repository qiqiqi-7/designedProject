package cn.edu.hbpu.education.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 收藏表	
 * </p>
 *
 * @author 一眼万年
 * @since 2021-05-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TPreferrence implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 收藏表的id编号
     */
    private Integer preferId;

    /**
     * 收藏表的学生id编号
     */
    private String preferStuid;

    /**
     * 收藏表的学生id编号
     */
    private String preferTeachid;

    private String preferTime;

    private Integer preferIsdelete;

    private TTeachaccount tTeachaccount;
}
