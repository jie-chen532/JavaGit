package com.msb.controller;

import com.msb.pojo.UserInfo;
import com.msb.service.AddUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

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
        return "/showPlayer.jsp";
    }


    @RequestMapping(value = "getAllPlayer", method = GET)
    @ResponseBody
    public List<UserInfo> listUser()
    {
        List<UserInfo> userInfos = addUserInfo.listUser();
        return userInfos;
    }
}
