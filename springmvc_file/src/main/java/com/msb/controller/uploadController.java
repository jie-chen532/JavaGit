package com.msb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class uploadController {

    @ResponseBody
    @RequestMapping("fileUpload.do")
    public String uploadFile(MultipartFile headPhoto, HttpServletRequest request) throws IOException {
        //获取磁盘中upload路径
        String upload = request.getServletContext().getRealPath("upload");
        //创建文件对象
        File file = new File(upload);
        //判断目录是否存在，若不存在直接创建
        if(file.exists())
        {
            //直接创建多层目录
            file.mkdirs();
        }
        //获取上传的文件名
        String originalFilename = headPhoto.getOriginalFilename();
        //获取拓展名
        String extendName = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newFileName = UUID.randomUUID() + extendName;
        //生成文件存储位置
        File storageFile = new File(file, newFileName);
        //文件保存
        headPhoto.transferTo(storageFile);
        return "ok";
    }
}
