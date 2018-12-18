package com.wulong.project.web;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wulong.project.core.Result;
import com.wulong.project.core.ResultGenerator;
import com.wulong.project.email.EmailTemplet;
import com.wulong.project.email.MailConstant;
import com.wulong.project.email.entity.EmailMailInfo;
import com.wulong.project.email.jk.Provider;
import com.wulong.project.email.jk.RegMailSenderFactory;
import com.wulong.project.email.jk.Sender;
import com.wulong.project.email.service.EmailSendService;
import com.wulong.project.model.UserInfo;
import com.wulong.project.service.UserInfoService;
import com.wulong.project.slog.SLog;
import com.wulong.project.tool.CaptchaUtils;
import com.wulong.project.tool.FileDownloadUtils;
import com.wulong.project.tool.IpUtils;
import jdk.net.SocketFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @Author:wulong
 * @Date:2018/11/19 14:25
 * @mail:491925129@qq.com
 * @CrossOrigin 注解 支持跨域
 * @Transactional 事务
*/
@CrossOrigin
@Transactional(rollbackFor = Exception.class)
@RestController
@RequestMapping("/user/info")
public class UserInfoController {
    @Resource
    private UserInfoService userInfoService;

    @Value("${project.name}")
    private String projectName;

    /**
     * 邮件
     */
    /*@Resource
    private JavaMailSender javaMailSender;*/

    @PostMapping("/regist")
    @SLog(type = "regist",tag = "注册",msg = "用户账号注册")
    public Result add(@RequestBody(required = false) UserInfo userInfo, HttpServletRequest request, HttpSession session) {
        userInfo.setUserId(UUID.randomUUID().toString().replace("-",""));
        userInfo.setLoginIp(IpUtils.getIpAddr(request));
    	Map<String,Object> ipInfo = getIpInfo(userInfo.getLoginIp());
    	String code = ipInfo.get("code").toString();
    	System.out.println(code);
    	if (ipInfo.get("code").toString().equals("0")){
            Map<String,Object> data = JSON.parseObject(ipInfo.get("data").toString());
            userInfo.setCity(data.get("city").toString());
            userInfo.setCityId(data.get("city_id").toString());
            userInfo.setCountry(data.get("country").toString());
            userInfo.setCountryId(data.get("country_id").toString());
            userInfo.setRegion(data.get("region").toString());
            userInfo.setRegionId(data.get("region_id").toString());
            userInfo.setIsp(data.get("isp").toString());
            userInfo.setIspId(data.get("isp_id").toString());
    	}
    	// 默认禁用 需要邮箱验证
    	userInfo.setDisable(0);
    	userInfo.setDelFlag(0);
    	// 获取邮件注册工厂方法
        Provider provider = new RegMailSenderFactory();
        Sender sender = provider.produceEmail();
        Map<String,Object> result = sender.sendEmail(projectName,userInfo.getEmail(),session);
        if ((Boolean) result.get(MailConstant.EMAIL_SEND_STATUS)) {
            userInfoService.save(userInfo);
            return ResultGenerator.genSuccessResult(result.get(MailConstant.EMAIL_V_CODE));
        } else {
            return ResultGenerator.genFailResult("用户注册失败！");
        }
    }

    /**
     * 根据ip获取地理位置信息
     * @param ip
     * @return
     */
    private Map<String, Object> getIpInfo(String ip) {
        Map<String,Object> result = new HashMap<>();
        String json = FileDownloadUtils.getURLContent("116.62.4.184");
        return JSON.parseObject(json);
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        userInfoService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/test/{name}")
    public Map<String,Object> test(@PathVariable String name) {
        Map<String,Object> result = new HashMap<>();
        result.put("aava",name);
        return result;
    }

    @PostMapping("/update")
    public Result update(UserInfo userInfo) {
        userInfoService.update(userInfo);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        UserInfo userInfo = userInfoService.findById(id);
        return ResultGenerator.genSuccessResult(userInfo);
    }

    @PostMapping("/list")
    @SLog(type = "user",tag = "用户信息",msg = "获取用户列表信息")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<UserInfo> list = userInfoService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
