package com.springboott.ttdemo.util.excel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ExcelData implements Serializable {

    @ApiModelProperty(notes = "文件名称")
    private String fileName;

    @ApiModelProperty(notes = "表头数据")
    private List<String> head;

    @ApiModelProperty(notes = "数据")
    private List<List<String>> data;

}
