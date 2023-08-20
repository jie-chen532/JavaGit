package com.msb.controller;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
public class FileController {

    //tomcat上传路径
    private final String FILE_PATH = "http://192.168.1.4:8090/upload/";

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
        mapResult.put("fileName", newFileName);
        mapResult.put("fileContentType", headPhoto.getContentType());
        mapResult.put("message", "ok");
        return mapResult;
    }

    @RequestMapping("fileDownload.do")
    @ResponseBody
    public void fileDownload(String photoName, String fileType, HttpServletResponse response) throws IOException {
        //从入参获取文件名称和文件类型
        //设置响应头
        //1.告诉浏览器要将数据保存到磁盘上，不在浏览器上直接解析
        response.setHeader("Content-Disposition", "attachment;filename="+photoName);
        //2.告诉浏览器下载的文件类型
        response.setContentType(fileType);
        //从图片服务器获取输入流
        InputStream inputStream = new URL(FILE_PATH + photoName).openStream();
        //获取一个指向浏览器的输出流
        ServletOutputStream outputStream = response.getOutputStream();
        //向浏览器响应文件
        IOUtils.copy(inputStream, outputStream);
    }

}
