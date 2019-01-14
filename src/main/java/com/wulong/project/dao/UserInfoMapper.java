package com.wulong.project.dao;

import com.wulong.project.core.Mapper;
import com.wulong.project.model.UserInfo;
import org.apache.ibatis.annotations.Select;

public interface UserInfoMapper extends Mapper<UserInfo> {

    @Select("update user_info set disable = 0 where user_name = #{userName}")
    void updateUndisableByUserName(String userName);
}