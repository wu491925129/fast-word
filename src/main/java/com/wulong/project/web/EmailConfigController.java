package com.wulong.project.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wulong.project.core.Result;
import com.wulong.project.core.ResultGenerator;
import com.wulong.project.email.MailConstant;
import com.wulong.project.model.EmailConfigInfo;
import com.wulong.project.service.EmailConfigService;
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


    /**
     * 邮件验证
     * @param email
     * @param vCode
     * @param session
     * @return
     */
    @PostMapping("/check")
    public Result registCheck(String email,String type, String vCode, HttpSession session) {
        String code;
        switch (type) {
            case MailConstant.REGIST_SESSION_ATTR:
                code = (String) session.getAttribute(MailConstant.REGIST_SESSION_ATTR+":"+email);
                if (vCode.equals(code)) {
                    return ResultGenerator.genSuccessResult();
                } else {
                    return ResultGenerator.genFailResult("验证码错误!");
                }
            case MailConstant.RESET_SESSION_ATTR:
                code = (String) session.getAttribute(MailConstant.RESET_SESSION_ATTR+":"+email);
                if (vCode.equals(code)) {
                    return ResultGenerator.genSuccessResult();
                } else {
                    return ResultGenerator.genFailResult("验证码错误!");
                }
             default:
                    return ResultGenerator.genFailResult("未知错误");
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
