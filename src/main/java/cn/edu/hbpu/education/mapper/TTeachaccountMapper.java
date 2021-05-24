package cn.edu.hbpu.education.mapper;

import cn.edu.hbpu.education.entity.TTeachaccount;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 一眼万年
 * @since 2021-05-07
 */
public interface TTeachaccountMapper extends BaseMapper<TTeachaccount> {
    @Select("select teach.*  from t_teachaccount teach where teach_id=#{teach_id}")
    @Results({
            @Result(property = "teachId",column = "teach_id"),
            @Result(property = "teachAccount",column = "teach_account"),
            @Result(property = "teachHeadicon",column = "teach_headicon"),
            @Result(property = "teachGrade",column = "teach_grade"),
            @Result(property = "teachMajor",column = "teach_major"),
            @Result(property = "teachGracredit",column = "teach_gracredit"),
            @Result(property = "teachType",column = "teach_type"),
            @Result(property = "teachExperience",column = "teach_experience"),
            @Result(property = "tUser",javaType = cn.edu.hbpu.education.entity.TUser.class,column = "teach_id",
                one=@One(select ="cn.edu.hbpu.education.mapper.TUserMapper.getUserByUserCode"))
    })
    public TTeachaccount getTeachInfo(@RequestParam("teach_id") String teach_id);

    @Select("select teach.teach_id ,teach.teach_account,teach.teach_headicon from t_teachaccount teach right join t_ability ability on teach.teach_id = ability.ability_teachid where ability.ability_course in " +
            "(select course.course_id from t_course course left join t_course course1 on course.course_prev = course1.course_id   where course1.course_id = #{course_id})")
    public List<Map<String,Object>> getTeachInfoByCourseId(String course_id);
}
