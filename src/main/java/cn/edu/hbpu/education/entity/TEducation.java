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
public class TEducation implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 教育表id编号
     */
    private Integer educationId;

    /**
     * 教师账户id编号
     */
    private String teacherId;

    /**
     * 证书的编码
     */
    private String educationCode;

    /**
     * 教师账户获得证书的编码
     */
    private String educationVertified;


}
