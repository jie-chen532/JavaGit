package com.msb.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserInfo implements Serializable {
    private int id;
    private String userName;
    private String password;
    private String nickName;
    private String photoName; //文件名称
    private String fileType; //文件类型
}
