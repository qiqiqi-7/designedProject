package cn.edu.hbpu.education.mapper;

import cn.edu.hbpu.education.entity.TPreferrence;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;

/**
 * <p>
 * 收藏表	 Mapper 接口
 * </p>
 *
 * @author 一眼万年
 * @since 2021-05-07
 */
public interface TPreferrenceMapper extends BaseMapper<TPreferrence> {
    /*
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
    public List<TAbility> getCourseInfo(String teachId);*/
    @Select("select * from t_preferrence where prefer_stuid = #{usercode}")
    @Results({
            @Result(property = "tTeachaccount",javaType = cn.edu.hbpu.education.entity.TTeachaccount.class,
            column = "prefer_teachid",
            one=@One(select = "cn.edu.hbpu.education.mapper.TTeachaccountMapper.getTeachInfo"))
    })
    public List<TPreferrence> getPreferList(String usercode);

    @Options(statementType = StatementType.CALLABLE)
    @Select(("call education_plus.upAndDelete(#{userid,mode=IN,jdbcType=VARCHAR},#{teachid,mode=IN,jdbcType=VARCHAR})"))
    public int  alterPreferStatus(String userid,String teachid);

}
