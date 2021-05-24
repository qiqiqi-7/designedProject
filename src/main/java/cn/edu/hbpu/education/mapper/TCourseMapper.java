package cn.edu.hbpu.education.mapper;

import cn.edu.hbpu.education.entity.TCourse;
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
public interface TCourseMapper extends BaseMapper<TCourse> {
    /*
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
     */
    @Select("select course_id,course_name,course_image  from t_course where course_id=#{courseId}")
    public TCourse getCourseById(@Param("courseId") String courseId);
}
