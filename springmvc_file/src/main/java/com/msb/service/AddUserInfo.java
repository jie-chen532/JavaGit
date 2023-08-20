package com.msb.service;

import com.msb.pojo.UserInfo;

import java.util.List;

public interface AddUserInfo {
    int add(UserInfo userInfo);

    List<UserInfo> listUser();
}
