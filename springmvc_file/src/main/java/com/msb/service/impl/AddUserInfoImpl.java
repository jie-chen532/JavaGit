package com.msb.service.impl;

import com.msb.mapper.UserMapper;
import com.msb.pojo.UserInfo;
import com.msb.service.AddUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddUserInfoImpl implements AddUserInfo {

    @Autowired
    private UserMapper userMapper;

    public int add(UserInfo userInfo) {

        int row = userMapper.addUserInfo(userInfo);
        return row;
    }

    public List<UserInfo> listUser() {
        return userMapper.listInfo();
    }

}
