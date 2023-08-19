package com.msb.controller;

import com.msb.pojo.UserInfo;
import com.msb.service.AddUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PlayerController {
    @Autowired
    private AddUserInfo addUserInfo;


    @RequestMapping("addPlayer")
    public String addUserInfo(UserInfo userInfo)
    {
        int rows = addUserInfo.add(userInfo);
        if(rows == 0)
        {
            return "/html/fail.html";
        }
        return "/html/showPlayer.html";
    }
}
