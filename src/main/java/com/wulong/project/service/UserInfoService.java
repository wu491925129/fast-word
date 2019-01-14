package com.wulong.project.service;
import com.wulong.project.model.UserInfo;
import com.wulong.project.core.Service;
import org.apache.ibatis.annotations.Select;


/**
 * Created by CodeGenerator on 2018/11/21.
 */
public interface UserInfoService extends Service<UserInfo> {

    void updateUserStatus(String userName);
}
