package cn.edu.hbpu.education.service;

import cn.edu.hbpu.education.entity.TPreferrence;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 收藏表	 服务类
 * </p>
 *
 * @author 一眼万年
 * @since 2021-05-07
 */
public interface ITPreferrenceService extends IService<TPreferrence> {
    public List<TPreferrence> getPreferList(String usercode);

    public int alterStatus(String userid,String teachid);

}
