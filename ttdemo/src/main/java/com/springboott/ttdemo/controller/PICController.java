package com.springboott.ttdemo.controller;

import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@Controller
public class PICController {
    private Logger log;

    @RequestMapping(value = "/aaa")
    public String aaa() {
        return "index";
    }

    @ResponseBody
    @RequestMapping(value = "/home",method = RequestMethod.GET)
    public String home() {
        return "Hello, world";
    }

    @RequestMapping(value = "ReSavePic",method = RequestMethod.GET)
    public void ReSavePic(Pictt pictt){
        System.out.println(pictt);
        System.out.println(pictt.getName());
    }


    @ResponseBody
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(@RequestParam("file") MultipartFile files, HttpServletRequest request, HttpServletResponse response) throws IOException {
        //JSONObject json=new JSONObject();
        response.setCharacterEncoding("utf-8");
        String msg = "添加成功";
        log.info("-------------------开始调用上传文件upload接口-------------------");
        try{
            String name = files.getOriginalFilename();
            String path = this.getClass().getClassLoader().getResource("/").getPath();
            int index = path.indexOf("Shopping");
            path = path.substring(0, index + "Shopping".length()) + "/WebContent/resources/upload/";
            path = path + File.separator + name;
            System.out.println(path);

            //File uploadFile = new File(path);
            //files.transferTo(uploadFile);
        }catch(Exception e){
            msg="添加失败";
        }
        log.info("-------------------结束调用上传文件upload接口-------------------");
        //json.put("msg", msg);
        //return BuildJsonOfObject.buildJsonOfJsonObject(json);
        return "成功";
    }

}
