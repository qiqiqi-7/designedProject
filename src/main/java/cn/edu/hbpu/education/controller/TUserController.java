package cn.edu.hbpu.education.controller;


import cn.edu.hbpu.education.entity.TUser;
import cn.edu.hbpu.education.service.impl.MailService;
import cn.edu.hbpu.education.service.impl.TUserServiceImpl;
import cn.edu.hbpu.education.utils.RandomVericodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
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
@RequestMapping("/education/t-user")
public class TUserController {
    @Autowired
    private TUserServiceImpl tUserService;
    @Autowired
    private MailService mailService;

    @RequestMapping("/compelteInfo")
    public Map<String,String> completeInfo(@RequestBody TUser tUser,HttpSession session){
        Map<String,String> map = new HashMap<>();
        System.out.println(tUser.getVericode());
        if(!session.getAttribute("vericode").equals(tUser.getVericode())){
            map.put("status","false");
        }else {
            boolean save = tUserService.save(tUser);
            map.put("status","true");
            if (save == true) {
                map.put("result", "success");
            } else
                map.put("result", "error");
        }
        return map;
    }

    @RequestMapping("/sendMail")
    public String sendMail(String toUser, HttpSession httpSession){
        String vericode = RandomVericodeUtil.getRandomVericode();
        mailService.sendMail(toUser,"家教网在线",vericode);
        httpSession.setAttribute("vericode",vericode);
        return "success";
    }
}

