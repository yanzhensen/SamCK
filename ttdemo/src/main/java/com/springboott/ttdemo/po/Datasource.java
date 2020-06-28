package com.springboott.ttdemo.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 数据源表
 * </p>
 *
 * @author Sam
 * @since 2020-04-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("datasource")
@ApiModel(value="Datasource对象", description="数据源表")
public class Datasource implements Serializable {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "数据源id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "公司名英文缩写")
    private String name;

    @ApiModelProperty(value = "公司名中文全称")
    private String companyName;

    @ApiModelProperty(value = "公司联系人")
    private String companyContact;

    @ApiModelProperty(value = "公司电话")
    private String companyTel;

    @ApiModelProperty(value = "公司地址")
    private String companyAddress;

    @ApiModelProperty(value = "数据库名")
    private String dbName;

    @ApiModelProperty(value = "JDBC驱动")
    private String driver;

    @ApiModelProperty(value = "URL路径")
    private String url;

    @ApiModelProperty(value = "数据库用户名")
    private String username;

    @ApiModelProperty(value = "数据库用户密码")
    private String password;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "登记时间")
    private LocalDateTime createTime;


}
