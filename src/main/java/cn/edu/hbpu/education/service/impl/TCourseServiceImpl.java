package cn.edu.hbpu.education.service.impl;

import cn.edu.hbpu.education.entity.TCourse;
import cn.edu.hbpu.education.mapper.TCourseMapper;
import cn.edu.hbpu.education.service.ITCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
public class TCourseServiceImpl extends ServiceImpl<TCourseMapper, TCourse> implements ITCourseService {

}
