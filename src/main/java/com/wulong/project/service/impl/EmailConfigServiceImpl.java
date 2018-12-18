package com.wulong.project.service.impl;

import com.wulong.project.core.AbstractService;
import com.wulong.project.dao.EmailConfigMapper;
import com.wulong.project.model.EmailConfigInfo;
import com.wulong.project.service.EmailConfigService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2018/12/18.
 */
@Service
@Transactional
public class EmailConfigServiceImpl extends AbstractService<EmailConfigInfo> implements EmailConfigService {
    @Resource
    private EmailConfigMapper emailConfigMapper;

}
