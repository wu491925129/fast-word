package com.wulong.project.web;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Strings;
import com.wulong.project.core.Result;
import com.wulong.project.core.ResultGenerator;
import com.wulong.project.model.User;
import com.wulong.project.model.UserInfo;
import com.wulong.project.redis.service.RedisManagerService;
import com.wulong.project.service.UserInfoService;
import com.wulong.project.slog.SLog;
import com.wulong.project.tool.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Condition;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: wulong
 * @Date: 2019/1/24 11:00
 * @Email: 491925129@qq.com
 */
@CrossOrigin
@RestController
public class LoginController {

    /**
     * 当前激活的配置文件
     */
    @Value("${spring.profiles.active}")
    private String env;

    /**
     * token过期时间
     */
    @Value("${project.token.expiredTime}")
    private int expiredTime;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private RedisManagerService redisManagerService;

    @PostMapping("/login")
    @SLog(type = "login",tag = "登陆",msg = "用户登陆")
    public Result toLogin(@RequestParam(value="userName",required=false) String userName, @RequestParam(value="password",required=false) String password) {
        if (Strings.isNullOrEmpty(userName) || Strings.isNullOrEmpty(password)) {
            return ResultGenerator.genFailResult("用户名或者密码不能为空！");
        }
        // 首先从redis判断是否有用户信息
        Map<String,Object> result = checkUser(userName,password);
        if ((Boolean) result.get("status")) {
            User user = new User();
            UserInfo userInfo = (UserInfo) result.get("userInfo");
            user.setUserId(userInfo.getUserId());
            user.setUserName(userInfo.getUserName());
            user.setDisplayName(userInfo.getDisplayName());
            user.setAvatarUrl(userInfo.getAvatarUrl());
            if (userInfo.getGold() != null) {
                user.setGold(userInfo.getGold());
            }
            if (userInfo.getMyLevel() != null) {
                user.setMyLevel(userInfo.getMyLevel());
            }
            user.setLikeTag(userInfo.getLikeTag());
            Map<String,Object> obj = new HashMap<>();
            obj.put("userInfo",user);
            if (!"dev".equals(env)) {
                // 非开发模式下返回token
                String token = JwtUtils.createJWT(user.getUserId(),JSON.toJSONString(user),expiredTime);
                obj.put("auth_token",token);
            }
            return ResultGenerator.genSuccessResult(obj);
        } else {
            return ResultGenerator.genFailResult((String) result.get("msg"));
        }
    }

    /**
     * 首先从redis中判断用户是否存在且账号密码正确
     * @param userName
     * @param password
     * @return
     */
    private Map<String,Object> checkUser(String userName, String password) {
        Map<String,Object> result = new HashMap<>();
        UserInfo userInfo = JSON.parseObject(redisManagerService.redisGetByKey(userName + ":userInfo"),UserInfo.class);
        if (userInfo == null) {
            return dbCheck(userName,password);
        }
        // 返回状态  密码正确且没有被禁用
        result.put("userInfo",userInfo);
        result.put("status",true);
        if (!userInfo.getPassword().equals(password)) {
            result.put("status", false);
            result.put("msg","密码错误");
        }
        if (userInfo.getDisable() == 0) {
            result.put("status", false);
            result.put("msg","账号被禁用");
        }
        return result;
    }

    /**
     * 从数据库中判断
     * @param userName
     * @param password
     * @return
     */
    private Map<String,Object> dbCheck(String userName, String password) {
        Map<String,Object> result = new HashMap<>();
        Condition condition = new Condition(UserInfo.class);
        condition.createCriteria().andEqualTo("userName",userName).andEqualTo("password",password);
        List<UserInfo> infoList = userInfoService.findByCondition(condition);
        if (infoList.size() == 0) {
            result.put("status",false);
            result.put("msg","账号不存在或密码错误");
            return result;
        }else if (infoList.get(0).getDisable() == 0) {
            result.put("status",false);
            result.put("msg","账号被禁用");
            return result;
        }else {
            result.put("status",true);
            // 将用户信息插入redis
            redisManagerService.redisSetObject(userName+":userInfo",infoList.get(0));
        }
        result.put("userInfo",infoList.get(0));
        return result;
    }
}
