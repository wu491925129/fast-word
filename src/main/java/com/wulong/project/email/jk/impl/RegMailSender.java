package com.wulong.project.email.jk.impl;

import com.wulong.project.email.EmailTemplet;
import com.wulong.project.email.entity.EmailMailInfo;
import com.wulong.project.email.jk.Sender;
import com.wulong.project.email.service.EmailSendService;
import com.wulong.project.model.EmailConfigInfo;
import com.wulong.project.service.EmailConfigService;
import com.wulong.project.tool.CaptchaUtils;
import com.wulong.project.tool.SpringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: wulong
 * @Date: 2018/12/18 15:12
 * @Email: 491925129@qq.com
 */
public class RegMailSender implements Sender {

    private static final Logger logger = LoggerFactory.getLogger(RegMailSender.class);

    @Override
    public Map<String,Object> sendEmail(String fromName,String eMail, HttpSession session) {
        EmailConfigService emailConfigService = SpringUtil.getBean(EmailConfigService.class);
        EmailConfigInfo info = emailConfigService.findBy("type","regist");
        Map<String,Object> result = new HashMap<>();
        String captcha = CaptchaUtils.creatCaptcha();
        session.setAttribute("regist:"+eMail, captcha);
        String title ="用户注册 验证码["+captcha+"]";
        String content = EmailTemplet.getHtml("用户注册",eMail,"注册",captcha);
        info.setReceiveEmail(eMail);
        info.setSubject(title);
        info.setContent(content);
        info.setType("regist");
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
        return result;
    }
}
