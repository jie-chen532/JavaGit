package com.msb.controller;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
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

    //tomcat上传路径
    private final String FILE_PATH = "http://192.168.1.6:8090/upload/";

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
        //创建 sun公司提供的jersey包中的client对象
        Client client = Client.create();
        WebResource webResource = client.resource(FILE_PATH + newFileName);
        //将文件存储到服务器
        //String.class是返回值类型的字节码，代表返回值类型是String
        webResource.put(String.class, headPhoto.getBytes());
        //返回文件名和文件路径,供前端回显
        mapResult.put("fileName", FILE_PATH + newFileName);
        mapResult.put("fileContentType", headPhoto.getContentType());
        mapResult.put("message", "ok");
        return mapResult;
    }
}
