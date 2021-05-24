package cn.edu.hbpu.education.controller;


import cn.edu.hbpu.education.entity.TCourse;
import cn.edu.hbpu.education.service.impl.TCourseServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sun.media.jfxmediaimpl.HostUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 一眼万年
 * @since 2021-05-07
 */
@RestController
@RequestMapping("/education/t-course")
public class TCourseController {
    @Autowired
    private TCourseServiceImpl tCourseService;

    @RequestMapping("/getTopPicture")
    public Page<TCourse> getTopPictures(){
        QueryWrapper<TCourse> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("course_id,course_image,course_name");
        queryWrapper.orderByAsc("course_id");
        Page<TCourse> page = new Page<>(0,4,4);
        Page<TCourse> coursePage = tCourseService.page(page, queryWrapper);
        return coursePage;
    }

    @RequestMapping("/getCourseList")
    public List<Map<String ,Object>> getList(){
        QueryWrapper<TCourse> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("course_id,course_name");
        queryWrapper.ne("course_prev",1);

        List<Map<String, Object>> mapList = tCourseService.listMaps(queryWrapper);
        return mapList;
    }

    @RequestMapping("/test")
    public String  getTest(@RequestParam("ids") Integer []ids){

        for (Integer id : ids) {
            System.out.println(id);
        }
        return "error";
    }

}

