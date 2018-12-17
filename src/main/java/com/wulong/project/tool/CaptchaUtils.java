package com.wulong.project.tool;

import java.util.Random;

/**
 * @Author: wulong
 * @Date: 2018/12/17 14:40
 * @Email: 491925129@qq.com
 */
public class CaptchaUtils {
    /**

     * Description：生成验证码
     * @return
     * @return String
     **/
    public static String creatCaptcha(){
        String captcha = captcha(6);
        return captcha;
    }

    /**
     * Description：生成随机数
     * @param charCount
     * @return
     * @return String
     **/
    public static String captcha(int charCount){
        String charValue = "";
        for (int i = 0; i < charCount; i++) {
            char c = (char) (randomInt(0, 10) + '0');
            charValue += String.valueOf(c);
        }
        return charValue;
    }

    public static int randomInt(int from, int to) {
        Random r = new Random();
        return from + r.nextInt(to - from);
    }
}
