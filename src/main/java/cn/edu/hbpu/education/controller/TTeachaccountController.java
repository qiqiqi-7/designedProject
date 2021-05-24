package cn.edu.hbpu.education.controller;

import cn.edu.hbpu.education.config.Props;
import cn.edu.hbpu.education.constparam.Const;
import cn.edu.hbpu.education.entity.TAbility;
import cn.edu.hbpu.education.entity.TCourse;
import cn.edu.hbpu.education.entity.TPreferrence;
import cn.edu.hbpu.education.entity.TTeachaccount;
import cn.edu.hbpu.education.service.ITPreferrenceService;
import cn.edu.hbpu.education.service.impl.TAbilityServiceImpl;
import cn.edu.hbpu.education.service.impl.TCourseServiceImpl;
import cn.edu.hbpu.education.service.impl.TTeachaccountServiceImpl;
import cn.edu.hbpu.education.utils.DateFormatUtil;
import cn.edu.hbpu.education.utils.MD5Code;
import cn.edu.hbpu.education.utils.UploadUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 一眼万年
 * @since 2021-05-07
 */
@RestController
@RequestMapping("/education/t-teachaccount")
public class TTeachaccountController {
    @Autowired
    private TTeachaccountServiceImpl tTeachaccountService;
    @Autowired
    private TAbilityServiceImpl tAbilityService;
    @Autowired
    private Props props;
    @Autowired
    private ITPreferrenceService itPreferrenceService;

    @RequestMapping("/regist")
    public String teachRegist(TTeachaccount tTeachaccount, MultipartFile imgFile, MultipartFile teachCertify, MultipartFile gradeCertify, MultipartFile breifFile, @RequestParam("number") String []number,@RequestParam("l_salary") String []l_salary,@RequestParam("h_salary")String []h_salary){
        MD5Code md5Code = new MD5Code();
        tTeachaccount.setTeachRegtime(DateFormatUtil.dateToString(new Date(), Const.pattern));
        tTeachaccount.setTeachPwsd(md5Code.getMD5ofStr(tTeachaccount.getTeachPwsd()));
        UUID uuid = UUID.randomUUID();
        String filename = null;
        String endwith = null;
        String newFilename = null;
        File file = null;
        if(Integer.valueOf(tTeachaccount.getTeachType())==0);
        else{
            if(imgFile.isEmpty()){
                return "error";
            }
            filename = teachCertify.getOriginalFilename();
            endwith = filename.substring(filename.lastIndexOf("."));
            newFilename = "teachCertify"+uuid.toString().replace("-","")+endwith;
            file = new File(props.getUploadpath(),newFilename);
            tTeachaccount.setTeachFile(newFilename);//教师资格证明
            try {
                FileUtils.copyInputStreamToFile(imgFile.getInputStream(),file);
                //imgFile.transferTo(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //头像、学历证明，简历证明
        if(imgFile.isEmpty()||gradeCertify.isEmpty()||breifFile.isEmpty()){
            return "error";
        }
        File imagefile1 = UploadUtil.getUploadFile("icon",imgFile.getOriginalFilename(),uuid.toString().replace("-",""), props.getUploadpath());
        File gradeCertifyfile1 = UploadUtil.getUploadFile("gradeCertify",imgFile.getOriginalFilename(),uuid.toString().replace("-",""), props.getUploadpath());
        File breifFile1 = UploadUtil.getUploadFile("breif",imgFile.getOriginalFilename(),uuid.toString().replace("-",""), props.getUploadpath());

        try{
            FileUtils.copyInputStreamToFile(imgFile.getInputStream(),imagefile1);
            //imgFile.transferTo(imagefile1);
            FileUtils.copyInputStreamToFile(gradeCertify.getInputStream(),gradeCertifyfile1);
            //gradeCertify.transferTo(gradeCertifyfile1);
            FileUtils.copyInputStreamToFile(breifFile.getInputStream(),breifFile1);
            //breifFile.transferTo(breifFile1);
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
        tTeachaccount.setTeachId(uuid.toString().replace("-",""));
        tTeachaccount.setTeachHeadicon(imagefile1.getName());
        tTeachaccount.setTeachLectfile(gradeCertifyfile1.getName());//学历证明
        tTeachaccount.setTeachIntroduce(breifFile1.getName());///教师个人简介

        TAbility tAbility = null;

        List<TAbility> tAbilityList = new ArrayList<>();

        for (int i = 0; i < number.length; i++) {
            tAbility = new TAbility();
            tAbility.setAbilityTeachid(tTeachaccount.getTeachId());
            tAbility.setAbilityCourse(number[i]);
            tAbility.setAbilityLowSalary(Integer.valueOf(l_salary[i]));
            tAbility.setAblityHighSalary(Integer.valueOf(h_salary[i]));
            tAbilityList.add(tAbility);
        }
        tAbilityService.saveBatch(tAbilityList);

        boolean save = tTeachaccountService.save(tTeachaccount);
        if(save == true){
            return "ok";
        }else{
            return "error";
        }
    }
    @RequestMapping("/login")
    public Map<String,Object> loginAsTeach(@RequestBody TTeachaccount tTeachaccount){
        MD5Code md5Code = new MD5Code();
        QueryWrapper<TTeachaccount> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("teach_id,teach_account,teach_headicon");
        queryWrapper.eq("teach_account",tTeachaccount.getTeachAccount());
        queryWrapper.eq("teach_pwsd",md5Code.getMD5ofStr(tTeachaccount.getTeachPwsd()));
        Map<String,Object> tTeachaccount1 = tTeachaccountService.getMap(queryWrapper);
        return tTeachaccount1;
    }

    @RequestMapping("/getTeachInfo")
    public Map<String,Object> getTeachInfo(String userCode,String userId){
        Map<String,Object> map = new HashMap<>();
        TTeachaccount tTeachaccount =tTeachaccountService.getTeachInfo(userCode);
        map.put("tTeachaccount",tTeachaccount);
        if("1".equals(userId)){
            map.put("isConcerning",false);
        }else{
            QueryWrapper<TPreferrence> queryWrapper = new QueryWrapper<>();
            queryWrapper.select("prefer_id");
            queryWrapper.eq("prefer_stuid",userId);
            queryWrapper.eq("prefer_teachid",userCode);
            TPreferrence one = itPreferrenceService.getOne(queryWrapper);
            if(one!=null){
                map.put("isConcerning",true);
            }else
                map.put("isConcerning",false);
        }
        return map;
    }

    @RequestMapping("/getTeachInfoById")
    public List<Map<String,Object>> getTeachInfByCourseId(String courseId){
        return tTeachaccountService.getTeachInfoByCourseId(courseId);
    }
}

