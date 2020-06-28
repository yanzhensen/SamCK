package com.springboott.ttdemo.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springboott.ttdemo.config.controller.SuperController;
import com.springboott.ttdemo.config.enums.ErrorCodeEnum;
import com.springboott.ttdemo.config.response.ApiResponses;
import com.springboott.ttdemo.po.User;
import com.springboott.ttdemo.service.UserService;
import com.springboott.ttdemo.config.exception.ApiAssert;
import com.springboott.ttdemo.config.response.Result;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author Sam
 * @since 2019-09-04
 */
@Api(tags = "用户管理类")
@RestController
@RequestMapping("/user")
public class UserController extends SuperController {
    @Autowired
    private UserService userService;

    @Autowired
    RedisTemplate<String, Object> redisTemplate;


    @ApiOperation(value = "获取用户列表", notes = "查询用户信息（分页）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "limit", value = "每页条数", paramType = "query"),
            @ApiImplicitParam(name = "offset", value = "当前条数", paramType = "query")
    })
    @GetMapping
    public ApiResponses<JSONObject> userList(@RequestParam(name = "search", defaultValue = "") String search,
                                            @RequestParam(name = "limit", defaultValue = "10") Integer limit,
                                            @RequestParam(name = "offset", defaultValue = "0") Integer offset,
                                            @RequestParam(name = "sort", defaultValue = "id") String sort,
                                            @RequestParam(name = "sortOrder", defaultValue = "false") Boolean sortOrder) {
        PageHelper.offsetPage(offset, limit);
        //新增模糊搜索
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.orderBy(true, sortOrder, sort)
                .lambda().like(User::getUsername, search);
        List<User> lr = userService.list(queryWrapper);
        PageInfo<User> userList = new PageInfo<User>(lr);
        JSONObject js = new JSONObject();
        js.put("rows", userList.getList());
        js.put("total", userList.getTotal());
        return success(js);
    }

    @ApiOperation(value = "新增用户", notes = "新增用户")
    @PostMapping
    public ApiResponses<Void> insertUser(@RequestBody User user) {
        System.out.println("user = " + user);
//        Boolean result = userService.save(user);
        ApiAssert.isTrue(ErrorCodeEnum.BAD_ADD_FAILURE, false);
        return success();
    }

    @ApiOperation(value = "修改用户信息", notes = "根据用户id修改用户信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "用户id", required = true, dataType = "Integer")
    @ApiResponse(code = 400, message = "参数没有填好", response = String.class)
    @PutMapping
    public Result<String> updateUser(@ModelAttribute User user) {
        System.out.println("==========" + user);
        Boolean result = userService.updateById(user);
        if (result) {
            return new Result<>(1, "成功", null);
        } else {
            return new Result<>(-1, "失败", null);
        }
    }

    @ApiOperation(value = "删除用户", notes = "根据用户id删除用户")
    @ApiImplicitParam(paramType = "path", name = "id", value = "用户id", required = true, dataType = "Integer")
    @ApiResponse(code = 400, message = "参数没有填好", response = String.class)
    @DeleteMapping(value = "/{id}")
    public Result<String> deleteUser(@PathVariable(value = "id") Integer id) {
        Boolean result = userService.removeById(id);
        if (result) {
            return new Result<>(1, "成功", null);
        } else {
            return new Result<>(-1, "失败", null);
        }
    }

    @ApiOperation(value = "获取用户列表", notes = "测试用post获取，返回Result")
    @GetMapping(value = "/abc")
    public Result<String> userList(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.lambda().eq(User::getUsername, "五位两");
//        queryWrapper.between("id",1,50);
        List<User> lr = userService.list(queryWrapper);
        if (!lr.isEmpty()) {
            String json = JSON.toJSONString(lr);
            return new Result<>(1, "成功", json);
        } else {
            return new Result<>(-1, "失败", null);
        }
    }

    @PostMapping(value = "/login")
    public ModelAndView login(User user, ModelAndView mv, HttpSession session) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(User::getUsername, user.getUsername()).eq(User::getPassword, user.getPassword());
        User u = userService.getOne(queryWrapper);
        if (u != null) {
            session.setAttribute("userInfo", u);
            //设置redis 并且设置过期时间
            redisTemplate.opsForValue().set("userInfo", u.getId().toString(), 60, TimeUnit.SECONDS);
            mv.setViewName("redirect:/go/main");
            return mv;
        }
        mv.addObject("error", "账号或者密码错误");
        mv.setViewName("redirect:/go/login");
        return mv;
    }

    @GetMapping(value = "/logout")
    public ModelAndView logout(ModelAndView mv, HttpSession session) {
        Object u = session.getAttribute("userInfo");
        if (u != null) {
            session.removeAttribute("userInfo");
        }
        mv.setViewName("redirect:/go/index");
        return mv;
    }

}

