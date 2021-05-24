package cn.edu.hbpu.education.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class TCertify implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 证书的id编号
     */
    @TableId(value = "certi_id", type = IdType.AUTO)
    private Integer certiId;

    /**
     * 证书的名称
     */
    private String certiName;

    /**
     * 证书的代码
     */
    private String certiCode;

    /**
     * 添加证书的管理员编号
     */
    private String certiAdmincode;


}
