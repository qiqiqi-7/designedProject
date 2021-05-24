package cn.edu.hbpu.education.service.impl;

import cn.edu.hbpu.education.entity.TUser;
import cn.edu.hbpu.education.mapper.TUserMapper;
import cn.edu.hbpu.education.service.ITUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 一眼万年
 * @since 2021-05-07
 */
@Service
public class TUserServiceImpl extends ServiceImpl<TUserMapper, TUser> implements ITUserService {

}
