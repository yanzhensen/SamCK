package com.springboott.ttdemo.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageJump {

    @ApiOperation(value="跳转用户列表", notes="")
    @RequestMapping("go/{url}")
    public String goUserList(@PathVariable(value = "url") String url){
        return url;
    }
}
