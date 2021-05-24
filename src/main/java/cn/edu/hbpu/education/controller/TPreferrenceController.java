package cn.edu.hbpu.education.controller;


import cn.edu.hbpu.education.constparam.Const;
import cn.edu.hbpu.education.entity.TPreferrence;
import cn.edu.hbpu.education.service.impl.TPreferrenceServiceImpl;
import cn.edu.hbpu.education.utils.DateFormatUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sun.corba.se.spi.ior.ObjectKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 收藏表	 前端控制器
 * </p>
 *
 * @author 一眼万年
 * @since 2021-05-07
 */
@RestController
@RequestMapping("/education/t-preferrence")
public class TPreferrenceController {
    @Autowired
    private TPreferrenceServiceImpl tPreferrenceService;

    @RequestMapping("/addPreference")
    public Map<String,String> addPreference(TPreferrence tPreferrence){
        Map<String,String> stringMap = new HashMap<>();
        tPreferrence.setPreferTime(DateFormatUtil.dateToString(new Date(), Const.pattern));
        boolean save = tPreferrenceService.save(tPreferrence);
        if(save){
            stringMap.put("result","success");
        }else {
            stringMap.put("result","error");
        }
        return stringMap;
    }

    @RequestMapping("/getCollectList")
    public List<TPreferrence> getCollectList(String usercode){
        return tPreferrenceService.getPreferList(usercode);
    }
    @RequestMapping("/cancalPrefer")
    public Map<String, Object> cancalPrefer(String usercode, String teachId){
        int result = tPreferrenceService.alterStatus(usercode, teachId);
        Map<String,Object> map = new HashMap<>();

        map.put("status",result);

        return map;
    }






}

