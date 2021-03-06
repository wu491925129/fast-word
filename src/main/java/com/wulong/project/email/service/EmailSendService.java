package com.wulong.project.email.service;

import com.wulong.project.email.entity.EmailMailInfo;
import com.wulong.project.model.EmailConfigInfo;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Date;
import java.util.Properties;

/**
 * @Author: wulong
 * @Date: 2018/12/17 14:56
 * @Email: 491925129@qq.com
 */
@Service
public class EmailSendService {

    /**
     * 发送HTML邮件
     * @param info
     * @throws Exception
     */
    public void sendHtmlMail(EmailConfigInfo info)throws Exception{
        Message message = getMessage(info);
        // MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
        Multipart mainPart = new MimeMultipart();
        // 创建一个包含HTML内容的MimeBodyPart
        BodyPart html = new MimeBodyPart();
        // 设置HTML内容
        html.setContent(info.getContent(), "text/html; charset=utf-8");
        mainPart.addBodyPart(html);
        // 将MiniMultipart对象设置为邮件内容
        message.setContent(mainPart);
        Transport.send(message);
    }


    /**
     * 发送文本邮件
     * @param info
     * @throws Exception
     */
    public void sendTextMail(EmailConfigInfo info) throws Exception {
        Message message = getMessage(info);
        //消息发送的内容
        message.setText(info.getContent());
        Transport.send(message);
    }

    private Message getMessage(EmailConfigInfo info) throws Exception{
        Properties p = System.getProperties() ;
        p.setProperty("mail.smtp.host", info.getHost());
        p.setProperty("mail.smtp.auth", "true");
        p.setProperty("mail.smtp.user", info.getFromEmail());
        p.setProperty("mail.smtp.pass", info.getFormPassword());
        p.setProperty("mail.smtp.port", "465");
        p.setProperty("mail.smtp.socketFactory.port", "465");
        p.setProperty("mail.smtp.ssl.enable", "true");
        p.setProperty("mail.smtp.debug","false");
        // 根据邮件会话属性和密码验证器构造一个发送邮件的session
        Session session = Session.getInstance(p, new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(p.getProperty("mail.smtp.user"),p.getProperty("mail.smtp.pass"));
            }
        });
        session.setDebug(true);
        Message message = new MimeMessage(session);
        //消息发送的主题
        message.setSubject(info.getSubject());
        //接受消息的人
        message.setReplyTo(InternetAddress.parse(info.getReceiveEmail()));
        //消息的发送者
        message.setFrom(new InternetAddress(p.getProperty("mail.smtp.user"),info.getFormName()));
        // 创建邮件的接收者地址，并设置到邮件消息中
        message.setRecipient(Message.RecipientType.TO,new InternetAddress(info.getReceiveEmail()));
        // 消息发送的时间
        message.setSentDate(new Date());

        return message ;
    }
}
