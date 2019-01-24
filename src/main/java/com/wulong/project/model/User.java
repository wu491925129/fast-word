package com.wulong.project.model;

/**
 * @Author: wulong
 * @Date: 2019/1/24 14:05
 * @Email: 491925129@qq.com
 *
 * user公开信息
 */
public class User {

    private String userId;

    private String userName;

    private String displayName;

    private int gold;

    private int myLevel;

    private String likeTag;

    private String loginTheme;

    private String avatarUrl;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getMyLevel() {
        return myLevel;
    }

    public void setMyLevel(int myLevel) {
        this.myLevel = myLevel;
    }

    public String getLikeTag() {
        return likeTag;
    }

    public void setLikeTag(String likeTag) {
        this.likeTag = likeTag;
    }

    public String getLoginTheme() {
        return loginTheme;
    }

    public void setLoginTheme(String loginTheme) {
        this.loginTheme = loginTheme;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
