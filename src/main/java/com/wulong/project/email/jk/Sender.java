package com.wulong.project.email.jk;

import com.wulong.project.email.entity.EmailMailInfo;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @Author: wulong
 * @Date: 2018/12/18 15:00
 * @Email: 491925129@qq.com
 */
public interface Sender {

    Map<String,Object> sendEmail(String fromName,String eMail, HttpSession session);

}
