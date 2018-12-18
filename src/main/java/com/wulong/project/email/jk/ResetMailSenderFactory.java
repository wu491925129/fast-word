package com.wulong.project.email.jk;

import com.wulong.project.email.jk.impl.ResetMailSender;

/**
 * @Author: wulong
 * @Date: 2018/12/18 15:20
 * @Email: 491925129@qq.com
 */
public class ResetMailSenderFactory implements Provider{

    @Override
    public Sender produceEmail() {
        return new ResetMailSender();
    }
}
