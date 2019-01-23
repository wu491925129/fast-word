package com.wulong.project.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "upload_info")
public class UploadInfo {
    /**
     * 文件id
     */
    @Id
    @Column(name = "file_id")
    private String fileId;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 文件后缀
     */
    @Column(name = "file_suffix")
    private String fileSuffix;

    /**
     * 操作时间
     */
    @Column(name = "op_time")
    private Date opTime;

    /**
     * 文件状态
     */
    private String status;

    /**
     * 文件名
     */
    @Column(name = "file_name")
    private String fileName;

    /**
     * 文件描述信息
     */
    @Column(name = "file_desc")
    private String fileDesc;

    /**
     * 扩展字段1
     */
    @Column(name = "ext_1")
    private String ext1;

    /**
     * 扩展字段2
     */
    @Column(name = "ext_2")
    private String ext2;

    /**
     * 扩展字段3
     */
    @Column(name = "ext_3")
    private String ext3;

    /**
     * 获取文件id
     *
     * @return file_id - 文件id
     */
    public String getFileId() {
        return fileId;
    }

    /**
     * 设置文件id
     *
     * @param fileId 文件id
     */
    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    /**
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取文件后缀
     *
     * @return file_suffix - 文件后缀
     */
    public String getFileSuffix() {
        return fileSuffix;
    }

    /**
     * 设置文件后缀
     *
     * @param fileSuffix 文件后缀
     */
    public void setFileSuffix(String fileSuffix) {
        this.fileSuffix = fileSuffix;
    }

    /**
     * 获取操作时间
     *
     * @return op_time - 操作时间
     */
    public Date getOpTime() {
        return opTime;
    }

    /**
     * 设置操作时间
     *
     * @param opTime 操作时间
     */
    public void setOpTime(Date opTime) {
        this.opTime = opTime;
    }

    /**
     * 获取文件状态
     *
     * @return status - 文件状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置文件状态
     *
     * @param status 文件状态
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取文件名
     *
     * @return file_name - 文件名
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * 设置文件名
     *
     * @param fileName 文件名
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * 获取文件描述信息
     *
     * @return file_desc - 文件描述信息
     */
    public String getFileDesc() {
        return fileDesc;
    }

    /**
     * 设置文件描述信息
     *
     * @param fileDesc 文件描述信息
     */
    public void setFileDesc(String fileDesc) {
        this.fileDesc = fileDesc;
    }

    /**
     * 获取扩展字段1
     *
     * @return ext_1 - 扩展字段1
     */
    public String getExt1() {
        return ext1;
    }

    /**
     * 设置扩展字段1
     *
     * @param ext1 扩展字段1
     */
    public void setExt1(String ext1) {
        this.ext1 = ext1;
    }

    /**
     * 获取扩展字段2
     *
     * @return ext_2 - 扩展字段2
     */
    public String getExt2() {
        return ext2;
    }

    /**
     * 设置扩展字段2
     *
     * @param ext2 扩展字段2
     */
    public void setExt2(String ext2) {
        this.ext2 = ext2;
    }

    /**
     * 获取扩展字段3
     *
     * @return ext_3 - 扩展字段3
     */
    public String getExt3() {
        return ext3;
    }

    /**
     * 设置扩展字段3
     *
     * @param ext3 扩展字段3
     */
    public void setExt3(String ext3) {
        this.ext3 = ext3;
    }
}