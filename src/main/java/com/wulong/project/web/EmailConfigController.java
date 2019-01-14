package com.wulong.project.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wulong.project.core.Result;
import com.wulong.project.core.ResultGenerator;
import com.wulong.project.email.MailConstant;
import com.wulong.project.model.EmailConfigInfo;
import com.wulong.project.model.UserInfo;
import com.wulong.project.redis.service.RedisManagerService;
import com.wulong.project.service.EmailConfigService;
import com.wulong.project.service.UserInfoService;
import com.wulong.project.slog.SLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
* Created by CodeGenerator on 2018/12/18.
*/
@RestController
@RequestMapping("/email")
public class EmailConfigController {
    @Resource
    private EmailConfigService emailConfigService;

    @Autowired
    private RedisManagerService redisManagerService;

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 邮件验证
     * @param email
     * @param vCode
     * @param session
     * @return
     */
    @PostMapping("/check")
    @SLog(type = "registCheck",tag = "校验",msg = "用户账号注册验证码校验")
    public Result registCheck(String email,String type, String vCode, HttpSession session) {
        // 从redis中获取验证码
        String code = redisManagerService.redisGetByKey(email+":"+type);
        if (vCode.equals(code)) {
            // 验证通过 更新数据库和redis中用户状态为正常状态
            userInfoService.updateUserStatus(email);
            JSONObject userJson = JSON.parseObject(redisManagerService.redisGetByKey(email+":userInfo"));
            userJson.put("disable",0);
            redisManagerService.redisSetString(email+":userInfo",JSON.toJSONString(userJson));
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("验证码错误或者验证码已过期！");
        }
    }

    @PostMapping("/add")
    public Result add(EmailConfigInfo emailConfig) {
        emailConfigService.save(emailConfig);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        emailConfigService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(EmailConfigInfo emailConfig) {
        emailConfigService.update(emailConfig);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        EmailConfigInfo emailConfig = emailConfigService.findById(id);
        return ResultGenerator.genSuccessResult(emailConfig);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<EmailConfigInfo> list = emailConfigService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
