package cn.edu.hbpu.education.controller;


import cn.edu.hbpu.education.config.Props;
import cn.edu.hbpu.education.constparam.Const;
import cn.edu.hbpu.education.entity.TStudent;
import cn.edu.hbpu.education.entity.TTeachaccount;
import cn.edu.hbpu.education.service.impl.MailService;
import cn.edu.hbpu.education.service.impl.TStudentServiceImpl;
import cn.edu.hbpu.education.service.impl.TTeachaccountServiceImpl;
import cn.edu.hbpu.education.utils.DateFormatUtil;
import cn.edu.hbpu.education.utils.MD5Code;
import cn.edu.hbpu.education.utils.RandomVericodeUtil;
import com.baomidou.mybatisplus.core.conditions.query.Query;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 一眼万年
 * @since 2021-05-07
 */
@RestController
@RequestMapping("/education/t-student")
public class TStudentController {
    @Autowired
    private TStudentServiceImpl  tStudentService;
    @Autowired
    private TTeachaccountServiceImpl tTeachaccountService;
    @Autowired
    private MailService mailService;

    @Autowired
    private Props props;

    @RequestMapping("/regist")
    public String registStudentAccount(TStudent tStudent,@RequestParam("imgFile") MultipartFile file){
        tStudent.setStuRegtime(DateFormatUtil.dateToString(new Date(), Const.pattern));
        MD5Code md5Code = new MD5Code();
        if(file.isEmpty()){
            return "error";
        }
        String fileName = file.getOriginalFilename();
        //文件重命名
        String endwith = fileName.substring(fileName.lastIndexOf("."));

        String studentId = UUID.randomUUID().toString().replace("-","");
        tStudent.setStuId(studentId);
        tStudent.setStuPwsd(md5Code.getMD5ofStr(tStudent.getStuPwsd()));

        String newFilename = studentId+endwith;
        tStudent.setStuHeadicon(newFilename);

        String savePath = props.getUploadpath();
        File dest = new File(savePath,newFilename);
        try {
            file.transferTo(dest);
            tStudentService.save(tStudent);
            return "ok";
        }catch(IOException e) {
            e.printStackTrace();
        }
        return "error";
    }
    @RequestMapping("/login")
    public Map<String,Object> login(@RequestBody  TStudent tStudent,HttpSession session){
        MD5Code md5Code = new MD5Code();
        QueryWrapper<TStudent> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("stu_id,stu_account,stu_headicon")
            .eq("stu_account",tStudent.getStuAccount())
            .eq("stu_pwsd",md5Code.getMD5ofStr(tStudent.getStuPwsd()));

        return tStudentService.getMap(queryWrapper);
    }
    @RequestMapping("/validate")
    public String validation(String username,String status){
        QueryWrapper queryWrapper = new QueryWrapper();
        TStudent one = null;
        TTeachaccount serviceOne = null;
        if(status.equals("1")){
            queryWrapper.eq("stu_account",username);
            one = tStudentService.getOne(queryWrapper);
        }else{
            queryWrapper.eq("teach_account",username);
            serviceOne = tTeachaccountService.getOne(queryWrapper);
        }
        if(one!= null || serviceOne != null){
            return "error";
        }else
            return "ok";
    }

    @RequestMapping("/checkName")
    public Map<String,Object> checkName(String username,String type){
        System.out.println("username="+username+",type="+type);
        QueryWrapper queryWrapper = new QueryWrapper<>();
        Map<String,Object> map;
        String column="";
        if("0".equals(type)){
            column = "stu_account";
            queryWrapper.eq(column,username);
            queryWrapper.select("stu_id");
            map = tStudentService.getMap(queryWrapper);
        }else{
            column = "teach_account";
            queryWrapper.eq(column,username);
            queryWrapper.select("teach_id");
            map = tTeachaccountService.getMap(queryWrapper);
        }
        if(map!=null){
            map.put("status","0");
        }else{
            map = new HashMap<>();
            map.put("status","1");
        }
        return map;
    }


}

