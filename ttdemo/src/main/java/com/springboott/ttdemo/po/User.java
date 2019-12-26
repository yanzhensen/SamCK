package com.springboott.ttdemo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author Sam
 * @since 2019-09-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user")
@ApiModel(value = "User对象", description = "用户信息表")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(notes = "用户id", example = "1")
    private Integer id;

    @ApiModelProperty(notes = "用户名", example = "张三")
    private String username;

    @ApiModelProperty(notes = "用户密码", example = "123456")
    private String password;

    @ApiModelProperty(notes = "年龄", example = "27")
    private Integer age;

    @ApiModelProperty(notes = "联系电话", example = "13512312311")
    private String telephone;

    @ApiModelProperty(notes = "地址", example = "xx省xx市xx区xxxx")
    private String address;

    @ApiModelProperty(notes = "备注", example = "备注")
    private String remark;

    public User() {
    }

    public User(Integer id, String username, Integer age) {
        this.id = id;
        this.username = username;
        this.age = age;
    }

    public User(Integer id, String username, Integer age, String telephone) {
        this.id = id;
        this.username = username;
        this.age = age;
        this.telephone = telephone;
    }

}
