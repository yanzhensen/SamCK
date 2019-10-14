package com.springboott.ttdemo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 商品
 * </p>
 *
 * @author Sam
 * @since 2019-10-09
 */
public class Product implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String productName;

    private Integer productNum;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductNum() {
        return productNum;
    }

    public void setProductNum(Integer productNum) {
        this.productNum = productNum;
    }

    @Override
    public String toString() {
        return "Product{" +
        "id=" + id +
        ", productName=" + productName +
        ", productNum=" + productNum +
        "}";
    }
}
