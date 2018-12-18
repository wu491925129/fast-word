package com.wulong.project.email.jk.impl;

import com.wulong.project.email.EmailTemplet;
import com.wulong.project.email.entity.EmailMailInfo;
import com.wulong.project.email.jk.Sender;
import com.wulong.project.email.service.EmailSendService;
import com.wulong.project.tool.CaptchaUtils;
import com.wulong.project.tool.SpringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: wulong
 * @Date: 2018/12/18 15:13
 * @Email: 491925129@qq.com
 */
public class ResetMailSender implements Sender {

    private static final Logger logger = LoggerFactory.getLogger(ResetMailSender.class);

    @Override
    public Map<String,Object> sendEmail(String fromName,String eMail, HttpSession session) {
        /*Map<String,Object> result = new HashMap<>();
        String captcha = CaptchaUtils.creatCaptcha();
        session.setAttribute("resetPassword:"+eMail, captcha);
        String title = fromName +"密码重置 验证码["+captcha+"]";
        String content = EmailTemplet.getHtml(title,eMail,title,captcha);
        EmailMailInfo info = new EmailMailInfo();
        info.setReceiveEmail(eMail);
        info.setSubject(title);
        info.setContent(content);
        info.setType("reset");
        EmailSendService emailSendService = SpringUtil.getBean(EmailSendService.class);
        try {
            emailSendService.sendHtmlMail(info);
            result.put("status",true);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("status",false);
            logger.error("'" + title + "'的邮件发送失败！");
        }
        result.put("vCode",captcha);
        return result;*/
        return null;
    }
}
