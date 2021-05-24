package cn.edu.hbpu.education.service.impl;

import cn.edu.hbpu.education.config.MailConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MailService {
    @Resource
    private JavaMailSender javaMailSender;
    @Autowired
    private MailConfig mailConfig;
    public void sendMail(String to,String subject,String content){
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            //邮件的发送人
            simpleMailMessage.setFrom(mailConfig.getFromEmail());
            //邮件接收人
            simpleMailMessage.setTo(to);
            //邮件主题
            simpleMailMessage.setSubject(subject);
            //邮件的内容
            simpleMailMessage.setText(content);
            //发送邮件
            javaMailSender.send(simpleMailMessage);
    }







}
