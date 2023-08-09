package com.msb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
public class uploadController {

    @ResponseBody
    @RequestMapping("fileUpload.do")
    public String uploadFile(MultipartFile headPhoto) throws IOException {
        //指定文件存储目录
        File file = new File("/Users/jiechen/Documents/study/dataStudy/javaStudy/SpringMVC/springmvc_file/photo");
        //获取上传的文件名
        String originalFilename = headPhoto.getOriginalFilename();
        //生成文件存储位置
        File storageFile = new File(file, originalFilename);
        //文件保存
        headPhoto.transferTo(storageFile);
        return "ok";
    }
}
