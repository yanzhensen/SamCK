package com.springboott.ttdemo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;

public class UserClone implements Cloneable{
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

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
