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
public class TArea implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer areId;

    private String areCode;

    private String areName;

    private String cityCode;


}
