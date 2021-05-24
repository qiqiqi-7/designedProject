package cn.edu.hbpu.education.mapper;

import cn.edu.hbpu.education.entity.TAbility;
import cn.edu.hbpu.education.entity.TCourse;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 一眼万年
 * @since 2021-05-07
 */
public interface TAbilityMapper extends BaseMapper<TAbility> {
    /*

        private String abilityCourse;

    /**
     * 可函授科目的教师id编号
     */
    /*
    @TableField("ability_teachId")
    private String abilityTeachid;
    */
    /**
     * 可函授科目的最低薪水
     */
    /*
    private Integer abilityLowSalary;
    */
    /**
     * 可函授科目的最高薪水
     */
    /*
    private Integer ablityHighSalary;
     */


    @Select("select * from t_ability ability where ability.ability_teachId=#{teachId}")
    @Results({
            @Result(property = "abilityId",column = "ability_id",id = true),
            @Result(property = "abilityCourse",column = "ability_course"),
            @Result(property = "abilityTeachid",column = "ability_teachId"),
            @Result(property = "abilityLowSalary",column = "ability_low_salary"),
            @Result(property = "ablityHighSalary",column = "ablity_high_salary"),
            @Result(property = "tCourse",javaType = cn.edu.hbpu.education.entity.TCourse.class,column = "ability_course",
                    one=@One(select ="cn.edu.hbpu.education.mapper.TCourseMapper.getCourseById"))
    })
    public List<TAbility> getCourseInfo(String teachId);


}
