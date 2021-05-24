package cn.edu.hbpu.education.controller;


import cn.edu.hbpu.education.entity.Cascader;
import cn.edu.hbpu.education.entity.TArea;
import cn.edu.hbpu.education.entity.TCity;
import cn.edu.hbpu.education.entity.TProvinces;
import cn.edu.hbpu.education.service.impl.TAreaServiceImpl;
import cn.edu.hbpu.education.service.impl.TCityServiceImpl;
import cn.edu.hbpu.education.service.impl.TProvincesServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 一眼万年
 * @since 2021-05-07
 */
@RestController
@RequestMapping("/education/t-provinces")
public class TProvincesController {
    @Autowired
    private TProvincesServiceImpl tProvincesService;

    @Autowired
    private TCityServiceImpl tCityService;

    @Autowired
    private TAreaServiceImpl tAreaService;



    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @Cacheable(cacheNames = "provinceList", key = "123")
    public Cascader list() {

        Cascader cascader = new Cascader();

        QueryWrapper<TProvinces> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("pro_code,pro_name");
        List<Map<String, Object>> mapList = tProvincesService.listMaps(queryWrapper);
        Map<String,Object> stringMap = new HashMap<>();
        for (Map<String, Object> map : mapList) {
            stringMap.put(map.get("pro_code").toString(),map.get("pro_name"));
        }
        cascader.setProvince_list(stringMap);

        QueryWrapper<TCity> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.select("city_code,city_name");
        List<Map<String, Object>> mapList1 = tCityService.listMaps(queryWrapper1);
        Map<String,Object> stringMap1 = new HashMap<>();
        for (Map<String, Object> map : mapList1) {
            stringMap1.put(map.get("city_code").toString(),map.get("city_name").toString());
        }
        cascader.setCity_list(stringMap1);

        QueryWrapper<TArea> queryWrapper2 = new QueryWrapper<>();
        queryWrapper1.select("are_code,are_name");
        List<Map<String, Object>> mapList2 = tAreaService.listMaps(queryWrapper2);
        Map<String,Object> stringMap2 = new HashMap<>();
        for (Map<String, Object> map : mapList2) {
            stringMap2.put(map.get("are_code").toString(),map.get("are_name").toString());
        }
        cascader.setCounty_list(stringMap2);
        return cascader;
    }

    /*
    @RequestMapping(value = "/list123", method = RequestMethod.GET)
    @Cacheable(cacheNames = "product", key = "123")
    public String list() {

        // 1.查询所有上架商品
        List<ProductInfo> productInfoList = productInfoService.findUpAll();

        // 2.查询类目（一次性查询）
        //用 java8 的特性获取到上架商品的所有类型
       return null;
    }
    */

    @RequestMapping(value = "/updateList", method = RequestMethod.GET)
    @CachePut(cacheNames = "provinceList", key = "123")
    public Cascader updatelist() {
        Cascader cascader = new Cascader();

        QueryWrapper<TProvinces> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("pro_code,pro_name");
        List<Map<String, Object>> mapList = tProvincesService.listMaps(queryWrapper);
        Map<String,Object> stringMap = new HashMap<>();
        for (Map<String, Object> map : mapList) {
            stringMap.put(map.get("pro_code").toString(),map.get("pro_name"));
        }
        cascader.setProvince_list(stringMap);

        QueryWrapper<TCity> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.select("city_code,city_name");
        List<Map<String, Object>> mapList1 = tCityService.listMaps(queryWrapper1);
        Map<String,Object> stringMap1 = new HashMap<>();
        for (Map<String, Object> map : mapList1) {
            stringMap1.put(map.get("city_code").toString(),map.get("city_name").toString());
        }
        cascader.setCity_list(stringMap1);

        QueryWrapper<TArea> queryWrapper2 = new QueryWrapper<>();
        queryWrapper1.select("are_code,are_name");
        List<Map<String, Object>> mapList2 = tAreaService.listMaps(queryWrapper2);
        Map<String,Object> stringMap2 = new HashMap<>();
        for (Map<String, Object> map : mapList2) {
            stringMap2.put(map.get("are_code").toString(),map.get("are_name").toString());
        }
        cascader.setCounty_list(stringMap2);
        return cascader;
    }

    @RequestMapping(value = "/deleteList", method = RequestMethod.GET)
    @CachePut(cacheNames = "provinceList", key = "123")
    public void deleteList(){


    }

}

