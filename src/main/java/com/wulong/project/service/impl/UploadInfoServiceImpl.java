package com.wulong.project.service.impl;

import com.wulong.project.dao.UploadInfoMapper;
import com.wulong.project.model.UploadInfo;
import com.wulong.project.service.UploadInfoService;
import com.wulong.project.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2019/01/23.
 */
@Service
@Transactional
public class UploadInfoServiceImpl extends AbstractService<UploadInfo> implements UploadInfoService {
    @Resource
    private UploadInfoMapper uploadInfoMapper;

}
