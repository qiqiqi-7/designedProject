package cn.edu.hbpu.education.service.impl;

import cn.edu.hbpu.education.entity.TPreferrence;
import cn.edu.hbpu.education.mapper.TPreferrenceMapper;
import cn.edu.hbpu.education.service.ITPreferrenceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 收藏表	 服务实现类
 * </p>
 *
 * @author 一眼万年
 * @since 2021-05-07
 */
@Service
@Transactional(propagation= Propagation.NESTED,isolation= Isolation.DEFAULT,readOnly = false,rollbackFor=Exception.class)
public class TPreferrenceServiceImpl extends ServiceImpl<TPreferrenceMapper, TPreferrence> implements ITPreferrenceService {

    @Autowired
    TPreferrenceMapper tPreferrenceMapper;
    @Override
    public List<TPreferrence> getPreferList(String usercode) {
        return tPreferrenceMapper.getPreferList(usercode);
    }

    @Override
    public int alterStatus(String userid, String teachid) {
       return  tPreferrenceMapper.alterPreferStatus(userid, teachid);
    }
}
