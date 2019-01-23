package com.wulong.project.web;
import com.google.common.base.Strings;
import com.wulong.project.core.Result;
import com.wulong.project.core.ResultGenerator;
import com.wulong.project.model.UploadInfo;
import com.wulong.project.model.UserInfo;
import com.wulong.project.service.UploadInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
* Created by CodeGenerator on 2019/01/23.
*/
@RestController
@RequestMapping("/upload/info")
public class UploadInfoController {
    @Resource
    private UploadInfoService uploadInfoService;

    @PostMapping("/add")
    public Result add(@RequestBody(required = false)UploadInfo uploadInfo) {
        if (Strings.isNullOrEmpty(uploadInfo.getFileId())) {
            return ResultGenerator.genFailResult("文件上传失败！");
        }
        uploadInfo.setOpTime(new Date());
        uploadInfo.setFileSuffix(uploadInfo.getFileName().split("\\.")[uploadInfo.getFileName().split("\\.").length-1]);
        uploadInfo.setStatus("1");
        uploadInfoService.save(uploadInfo);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        uploadInfoService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(UploadInfo uploadInfo) {
        uploadInfoService.update(uploadInfo);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        UploadInfo uploadInfo = uploadInfoService.findById(id);
        return ResultGenerator.genSuccessResult(uploadInfo);
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public Result list(@RequestParam("userId") String userId,@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        Condition condition = new Condition(UploadInfo.class);
        condition.createCriteria().andEqualTo("userId",userId);
        List<UploadInfo> list = uploadInfoService.findByCondition(condition);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
