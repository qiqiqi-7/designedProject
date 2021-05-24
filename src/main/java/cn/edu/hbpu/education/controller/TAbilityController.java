package cn.edu.hbpu.education.controller;


import cn.edu.hbpu.education.entity.TAbility;
import cn.edu.hbpu.education.service.ITAbilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 一眼万年
 * @since 2021-05-07
 */
@RestController
@RequestMapping("/education/t-ability")
public class TAbilityController {
    @Autowired
    private ITAbilityService itAbilityService;
    @RequestMapping("/getListAbility")
    public List<TAbility> getListAbility(String teachId){
        return  itAbilityService.getListCourse(teachId);
    }
}

