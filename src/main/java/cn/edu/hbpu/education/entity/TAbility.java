package cn.edu.hbpu.education.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
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
public class TAbility implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 可函授科目的id
     */
    @TableId(value = "ability_id", type = IdType.AUTO)
    private Integer abilityId;

    /**
     * 可函授科目的id编号
     */
    private String abilityCourse;

    /**
     * 可函授科目的教师id编号
     */
    @TableField("ability_teachId")
    private String abilityTeachid;

    /**
     * 可函授科目的最低薪水
     */
    private Integer abilityLowSalary;

    /**
     * 可函授科目的最高薪水
     */
    private Integer ablityHighSalary;

    private transient TCourse tCourse;
}
