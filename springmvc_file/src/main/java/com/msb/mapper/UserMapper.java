package com.msb.mapper;

import com.msb.pojo.UserInfo;

import java.util.List;

public interface UserMapper {
    int addUserInfo(UserInfo userInfo);

    List<UserInfo> listInfo();
}
