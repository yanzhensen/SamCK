package com.springboott.ttdemo.controller;

import com.springboott.ttdemo.common.controller.SuperController;
import com.springboott.ttdemo.common.enums.ErrorCodeEnum;
import com.springboott.ttdemo.common.exception.ApiAssert;
import com.springboott.ttdemo.common.response.Result;
import com.springboott.ttdemo.service.ExcelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 微信相关接口
 *
 * @author szt21002
 * @since 2020-03-28
 */

@Api(tags = {"导出excel"})
@Validated
@Controller
@RequestMapping("/threadXls")
public class OutExcelRestController extends SuperController {

    @Autowired
    private ExcelService excelService;

    @ApiOperation(value = "导出", notes = "颜镇森2019-12-19：新增接口<br />")
    @GetMapping("/export")
    public void exportExcel(HttpServletRequest request, HttpServletResponse response, String fileName, Integer pageNum, Integer pageSize) {
        ApiAssert.isTrue(ErrorCodeEnum.INTERNAL_SERVER_ERROR.convert("文件名不能为空！"), StringUtils.isNotBlank(fileName));
        ApiAssert.isTrue(ErrorCodeEnum.INTERNAL_SERVER_ERROR.convert("文件格式有误！"), fileName.endsWith("xls"));
        Boolean isOk = excelService.exportExcel(request, response, fileName, 1, 10);
        ApiAssert.isTrue(ErrorCodeEnum.INTERNAL_SERVER_ERROR.convert("导出失败！"), isOk);
    }

    @ResponseBody
    @ApiOperation(value = "导入", notes = "颜镇森2019-12-19：新增接口<br />")
    @GetMapping("/import")
    public Result<String> importExcel(String fileName) {
        ApiAssert.isTrue(ErrorCodeEnum.INTERNAL_SERVER_ERROR.convert("文件名不能为空！"), StringUtils.isNotBlank(fileName));
        ApiAssert.isTrue(ErrorCodeEnum.INTERNAL_SERVER_ERROR.convert("文件格式有误！"), fileName.endsWith("xls"));
        Boolean isOk = excelService.importExcel(fileName);
        ApiAssert.isTrue(ErrorCodeEnum.INTERNAL_SERVER_ERROR.convert("导入失败！"), isOk);
        return new Result<>(200, "成功", null);
    }


}
