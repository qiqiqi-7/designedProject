package cn.edu.hbpu.education.service;

import cn.edu.hbpu.education.entity.TTeachaccount;
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
public interface ITTeachaccountService extends IService<TTeachaccount> {

    public TTeachaccount getTeachInfo(String usercode);


    public List<Map<String,Object>> getTeachInfoByCourseId(String couseId);

}
