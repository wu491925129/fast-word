package com.wulong.project.email.entity;

/**
 * @Author: wulong
 * @Date: 2018/12/17 14:57
 * @Email: 491925129@qq.com
 * 邮件内容实体
 */
public class EmailMailInfo {
    /**
     * 发送邮箱服务器
     */
    private String host ;
    /**
     * 发送者名称
     */
    private String formName ;
    /**
     * 发送邮箱密码或授权码
     */
    private String formPassword ;
    /**
     * 接收邮箱地址
     */
    private String receiveEmail ;
    /**
     * 发送者邮箱地址
     */
    private String fromEmail ;

    /**
     * 邮件类型
     */
    private String type;
    /**
     * 发送主题
     */
    private String subject ;
    /**
     * 发送内容
     */
    private String content ;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public String getFormPassword() {
        return formPassword;
    }

    public void setFormPassword(String formPassword) {
        this.formPassword = formPassword;
    }

    public String getReceiveEmail() {
        return receiveEmail;
    }

    public void setReceiveEmail(String receiveEmail) {
        this.receiveEmail = receiveEmail;
    }

    public String getFromEmail() {
        return fromEmail;
    }

    public void setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
