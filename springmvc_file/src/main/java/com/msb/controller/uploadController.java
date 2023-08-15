package com.msb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
public class uploadController {

    @ResponseBody
    @RequestMapping("fileUpload.do")
    public Map<String, String> uploadFile(MultipartFile headPhoto, HttpServletRequest request) throws IOException {
        Map<String, String> mapResult = new HashMap<String, String>();
        //控制文件大小
        if(headPhoto.getSize() > 1024 * 1024 * 5)
        {
            mapResult.put("message", "文件大小不能超过5MB");
            return mapResult;
        }
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
        //判断扩展名
        if(!extendName.equals(".jpg"))
        {
            mapResult.put("message", "文件不是jpg格式，不能进行上传");
            return mapResult;
        }
        //使用uuid替换文件名
        String newFileName = UUID.randomUUID() + extendName;
        //生成文件存储位置
        File storageFile = new File(file, newFileName);
        //文件保存
        headPhoto.transferTo(storageFile);
        //返回文件名和文件路径,供前端回显
        mapResult.put("fileName", newFileName);
        mapResult.put("fileContentType", headPhoto.getContentType());
        mapResult.put("message", "ok");
        return mapResult;
    }
}
