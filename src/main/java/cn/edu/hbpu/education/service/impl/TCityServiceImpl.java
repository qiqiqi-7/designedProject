package cn.edu.hbpu.education.service.impl;

import cn.edu.hbpu.education.entity.TCity;
import cn.edu.hbpu.education.mapper.TCityMapper;
import cn.edu.hbpu.education.service.ITCityService;
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
public class TCityServiceImpl extends ServiceImpl<TCityMapper, TCity> implements ITCityService {
    @Resource
    private TCityMapper tCityMapper;
    @Override
    public List<Map<String, Object>> mapList(List<Integer> listProCode) {








        return null;
    }
}
