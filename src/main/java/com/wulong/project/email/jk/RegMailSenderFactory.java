package com.wulong.project.email.jk;

import com.wulong.project.email.jk.impl.RegMailSender;

/**
 * @Author: wulong
 * @Date: 2018/12/18 15:17
 * @Email: 491925129@qq.com
 */
public class RegMailSenderFactory implements Provider {

    @Override
    public Sender produceEmail() {
        return new RegMailSender();
    }
}
