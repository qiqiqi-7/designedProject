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
public class TCity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer cityId;

    private String cityCode;

    private String cityName;

    private String proCode;


}
