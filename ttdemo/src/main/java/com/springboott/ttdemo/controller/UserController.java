package com.springboott.ttdemo.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboott.ttdemo.po.User;
import com.springboott.ttdemo.service.UserService;
import com.springboott.ttdemo.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author Sam
 * @since 2019-09-04
 */
@Api(value = "用户管理类")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;


    @ApiOperation(value="获取用户列表", notes="查询用户信息（分页）")
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public JSONObject userList(ModelAndView mv, User user,
                                 @RequestParam(name = "limit",defaultValue = "10") Integer limit,
                                 @RequestParam(name = "offset",defaultValue = "0") Integer offset
                               ){
        System.out.println("*****************"+offset);
        System.out.println("*****************"+limit);
        PageHelper.offsetPage(offset,limit);
        List<User> lr = userService.list();
        PageInfo<User> userList = new PageInfo<User>(lr);
        JSONObject js = new JSONObject();
        js.put("rows",userList.getList());
        js.put("total",userList.getTotal());
        return js;
    }

    @ApiOperation(value="获取用户列表", notes="")
    @RequestMapping(value = "/abc",method = RequestMethod.GET)
    public Result<String> userList(User user){
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.lambda().eq(User::getUsername, "五位两");
//        queryWrapper.between("id",1,50);
        List<User> lr = userService.list(queryWrapper);
        if(!lr.isEmpty()){
            String json = JSON.toJSONString(lr);
            return new Result<>(1,"成功",json);
        }else {
            return new Result<>(-1, "失败", null);
        }
    }

    @RequestMapping("/errorTest")
    public ModelAndView errorTest(ModelAndView mv, User user){
        user =  userService.getById(Integer.parseInt("sdsd"));
        System.out.println(user);
        mv.addObject(user);
        return mv;
    }
}

