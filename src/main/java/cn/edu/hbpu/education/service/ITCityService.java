package cn.edu.hbpu.education.service;

import cn.edu.hbpu.education.entity.TCity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 一眼万年
 * @since 2021-05-07
 */
public interface ITCityService extends IService<TCity> {
    public List<Map<String,Object>> mapList(List<Integer> listProCode);
}
