package cn.edu.hbpu.education.service.impl;

import cn.edu.hbpu.education.entity.TAbility;
import cn.edu.hbpu.education.entity.TCourse;
import cn.edu.hbpu.education.mapper.TAbilityMapper;
import cn.edu.hbpu.education.service.ITAbilityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 一眼万年
 * @since 2021-05-07
 */
@Service
public class TAbilityServiceImpl extends ServiceImpl<TAbilityMapper, TAbility> implements ITAbilityService {
    @Resource
    private TAbilityMapper tAbilityMapper;


    @Override
    public List<TAbility> getListCourse(String teachId) {
        return tAbilityMapper.getCourseInfo(teachId);
    }
}
