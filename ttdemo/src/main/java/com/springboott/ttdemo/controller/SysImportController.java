package com.springboott.ttdemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.springboott.ttdemo.common.enums.ErrorCodeEnum;
import com.springboott.ttdemo.common.exception.ApiAssert;
import com.springboott.ttdemo.common.response.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.openxml4j.exceptions.NotOfficeXmlFileException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.Objects;

@Api(tags = {"导入"})
@RestController
@RequestMapping("/import")
public class SysImportController {

    @ApiOperation(value = "一键式导入房源至出租", notes = "<br />请求参数示例：<br /><a href=\"https://pic-gongkai.fangzhizun.com/FnlcUkRa7g2F7IRWnJ_vOut79hrs8557.xlsx\" target=\"_blank\">点击下载模板：一键式导入至出租</a><br />" +
            "<br />严格按照模板excel模拟数据，数据最好为全新房源，防止重复房源而导致数据异常，文件大小限制10M" +
            "<br />颜镇森2019-01-08：新建接口<br />")
    @PostMapping("/housesToRentExcel")
    public Result<JSONObject> housesToRentExcel(@RequestParam("uploadFile") MultipartFile file) {
//        File file = new File(System.getProperty("user.dir") + "/src/main/resources/房源数据导入.xlsx");
//        File file = new File("C:\\Users\\Administrator\\Desktop\\员工信息.xlsx");
//        FileInputStream stream = null;
        InputStream stream = null;
        Workbook sheets = null;
        int beginRow = 3;//开始行
        int finishRow = 0;//完成行
        int totalRows = 0;//总行
        JSONObject resultObj = new JSONObject();//结果返回
        StringBuilder errorRows = new StringBuilder();//错误行
        StringBuilder res = new StringBuilder();//msg
        try {
            //获得该文件的输入流
//            stream = new FileInputStream(file);
            stream = file.getInputStream();
            // 多态  抛异常
            sheets = new XSSFWorkbook(stream);
            //获取一个工作表(sheet页)，下标从0开始
            Sheet sheet = sheets.getSheetAt(0);
            //模拟登录人信息
            totalRows = sheet.getLastRowNum() + 1 - beginRow;
            for (; beginRow <= sheet.getLastRowNum(); beginRow++) {
                // 获取行数
                Row row = sheet.getRow(beginRow);
                if (Objects.isNull(row)) {
                    continue;
                }
                try {
                    //进行事务操作 可回滚
                    //执行 所需要的代码
                    // 获取单元格 取值
                    Integer r1 = Integer.parseInt(StringUtils.isNotEmpty(row.getCell(0).getStringCellValue()) ? row.getCell(0).getStringCellValue() : "");//第一行第1列
                    String r2 = StringUtils.isNotEmpty(row.getCell(1).getStringCellValue()) ? row.getCell(1).getStringCellValue() : "";//第一行第2列
                    Double r3 = Double.parseDouble(StringUtils.isNotEmpty(row.getCell(2).getStringCellValue()) ? row.getCell(2).getStringCellValue() : "0");//第一行第3列数据
                    LocalDate r4 = LocalDate.parse(StringUtils.isNotEmpty(row.getCell(3).getStringCellValue()) ? row.getCell(3).getStringCellValue() : "");//第一行第4列数据
                    finishRow++;
                } catch (Exception e) {
                    if (StringUtils.isEmpty(errorRows)) {
                        errorRows.append("以下数据请检查数据正确性及完整性(未执行成功)：");
                    }
                    errorRows.append((beginRow + 1) + "、");
                    e.printStackTrace();
                    continue;
                }
            }
            //结果返回
            res.append("成功执行" + finishRow + "/" + totalRows);
            if (StringUtils.isNotEmpty(errorRows)) {
                res.append("," + errorRows);
                res.deleteCharAt(res.length() - 1);
                res.append("行");
            }
            resultObj.put("msg", res.toString());
            //关流
            sheets.close();
            stream.close();
        } catch (IOException e) {
            ApiAssert.isFalse(ErrorCodeEnum.INTERNAL_SERVER_ERROR.convert("excel读取流出错"), true);
        } catch (NotOfficeXmlFileException e) {
            ApiAssert.isFalse(ErrorCodeEnum.INTERNAL_SERVER_ERROR.convert("请使用.xls/.xlsx文件进行excel导入"), true);
        }
        return new Result<>(1, "成功", resultObj);
    }





}
