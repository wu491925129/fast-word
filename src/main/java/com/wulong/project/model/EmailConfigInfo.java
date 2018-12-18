package com.wulong.project.model;

import javax.persistence.*;

@Table(name = "email_config")
public class EmailConfigInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 发送邮箱服务器
     */
    private String host;

    /**
     * 发生者名称
     */
    @Column(name = "form_name")
    private String formName;

    /**
     * 发送者邮箱地址
     */
    @Column(name = "from_email")
    private String fromEmail;

    /**
     * 发送者邮箱密码或者授权码
     */
    @Column(name = "form_password")
    private String formPassword;

    /**
     * 接受邮箱地址
     */
    @Column(name = "receive_email")
    private String receiveEmail;

    /**
     * 邮箱对应发送邮件类型
     */
    private String type;

    /**
     * 发送邮件主题
     */
    private String subject;

    /**
     * 发送邮件内容
     */
    private String content;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取发送邮箱服务器
     *
     * @return host - 发送邮箱服务器
     */
    public String getHost() {
        return host;
    }

    /**
     * 设置发送邮箱服务器
     *
     * @param host 发送邮箱服务器
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * 获取发生者名称
     *
     * @return form_name - 发生者名称
     */
    public String getFormName() {
        return formName;
    }

    /**
     * 设置发生者名称
     *
     * @param formName 发生者名称
     */
    public void setFormName(String formName) {
        this.formName = formName;
    }

    /**
     * 获取发送者邮箱地址
     *
     * @return from_email - 发送者邮箱地址
     */
    public String getFromEmail() {
        return fromEmail;
    }

    /**
     * 设置发送者邮箱地址
     *
     * @param fromEmail 发送者邮箱地址
     */
    public void setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
    }

    /**
     * 获取发送者邮箱密码或者授权码
     *
     * @return form_password - 发送者邮箱密码或者授权码
     */
    public String getFormPassword() {
        return formPassword;
    }

    /**
     * 设置发送者邮箱密码或者授权码
     *
     * @param formPassword 发送者邮箱密码或者授权码
     */
    public void setFormPassword(String formPassword) {
        this.formPassword = formPassword;
    }

    /**
     * 获取接受邮箱地址
     *
     * @return receive_email - 接受邮箱地址
     */
    public String getReceiveEmail() {
        return receiveEmail;
    }

    /**
     * 设置接受邮箱地址
     *
     * @param receiveEmail 接受邮箱地址
     */
    public void setReceiveEmail(String receiveEmail) {
        this.receiveEmail = receiveEmail;
    }

    /**
     * 获取邮箱对应发送邮件类型
     *
     * @return type - 邮箱对应发送邮件类型
     */
    public String getType() {
        return type;
    }

    /**
     * 设置邮箱对应发送邮件类型
     *
     * @param type 邮箱对应发送邮件类型
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取发送邮件主题
     *
     * @return subject - 发送邮件主题
     */
    public String getSubject() {
        return subject;
    }

    /**
     * 设置发送邮件主题
     *
     * @param subject 发送邮件主题
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * 获取发送邮件内容
     *
     * @return content - 发送邮件内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置发送邮件内容
     *
     * @param content 发送邮件内容
     */
    public void setContent(String content) {
        this.content = content;
    }
}