package cn.edu.hbpu.education.mapper;

import cn.edu.hbpu.education.entity.TUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 一眼万年
 * @since 2021-05-07
 */
public interface TUserMapper extends BaseMapper<TUser> {
    @Select("select * from t_user where user_code = #{userCode}")
    public TUser getUserByUserCode(@Param("userCode") String userCode);
}
