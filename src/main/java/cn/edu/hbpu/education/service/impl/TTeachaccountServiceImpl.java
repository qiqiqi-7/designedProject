package cn.edu.hbpu.education.service.impl;

import cn.edu.hbpu.education.entity.TTeachaccount;
import cn.edu.hbpu.education.mapper.TTeachaccountMapper;
import cn.edu.hbpu.education.service.ITTeachaccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 一眼万年
 * @since 2021-05-07
 */
@Service
public class TTeachaccountServiceImpl extends ServiceImpl<TTeachaccountMapper, TTeachaccount> implements ITTeachaccountService {
    @Resource
    private TTeachaccountMapper tTeachaccountMapper;

    @Override
    public TTeachaccount getTeachInfo(String usercode) {
        return tTeachaccountMapper.getTeachInfo(usercode);
    }

    @Override
    public List<Map<String, Object>> getTeachInfoByCourseId(String couseId) {
        return tTeachaccountMapper.getTeachInfoByCourseId(couseId);
    }
}
