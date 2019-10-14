package com.springboott.ttdemo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author Sam
 * @since 2019-09-04
 */
@Api("用户实体类")
public class User implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(notes = "用户id")
    private Integer id;

    @ApiModelProperty(notes = "用户名")
    private String username;

    @ApiModelProperty(notes = "用户密码")
    private String password;

    @ApiModelProperty(notes = "年龄")
    private Integer age;

    @ApiModelProperty(notes = "联系电话")
    private String telephone;

    @ApiModelProperty(notes = "地址")
    private String address;

    @ApiModelProperty(notes = "备注")
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "User{" +
        "id=" + id +
        ", username=" + username +
        ", password=" + password +
        ", age=" + age +
        ", telephone=" + telephone +
        ", address=" + address +
        ", remark=" + remark +
        "}";
    }

}
