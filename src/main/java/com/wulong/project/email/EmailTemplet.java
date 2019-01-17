package com.wulong.project.email;

import org.springframework.util.ResourceUtils;

import java.io.*;

/**
 * @Author: wulong
 * @Date: 2018/12/17 14:47
 * @Email: 491925129@qq.com
 */
public class EmailTemplet {
    public static String getHtml(String title, String userName, String type, String captcha) {
        String emailTemplet = System.getProperty("emailTemplet");
        emailTemplet = emailTemplet.replace("$(title)", title);
        emailTemplet = emailTemplet.replace("$(userName)", userName);
        emailTemplet = emailTemplet.replace("$(type)", type);
        emailTemplet = emailTemplet.replace("$(captcha)", captcha);
        return emailTemplet;
    }
    /**
     * Description：容器初始化是加载
     * @return void
     **/
    public static void initEmailTemplet() {
        try {
            String encoding = "UTF-8";
            // 注：打包后会找不到静态文件，故使用类加载器流读取
            InputStream fileInputStream = EmailTemplet.class.getClassLoader().getResourceAsStream("emailTemplet.html");
            if (fileInputStream != null) { // 判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        fileInputStream, encoding);// 考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                StringBuilder sb = new StringBuilder();
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    sb.append(lineTxt);
                }
                System.setProperty("emailTemplet", sb.toString());
                read.close();
            } else {
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
    }
}
