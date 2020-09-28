package com.springboott.ttdemo.util.excel;

import com.springboott.ttdemo.util.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import static org.apache.poi.ss.usermodel.CellType.*;

/**
 * 导入导出
 */
@Slf4j
public class ExcelUtil {

    /**
     * 导出Excel
     *
     * @param request
     * @param response
     * @param data
     */
    public static void exportExcel(HttpServletRequest request, HttpServletResponse response, ExcelData data) {
        log.info("导出解析开始，fileName:{}", data.getFileName());
        try {
            //实例化HSSFWorkbook
            HSSFWorkbook workbook = new HSSFWorkbook();
            //创建一个Excel表单，参数为sheet的名字
            HSSFSheet sheet = workbook.createSheet("sheet");
            //设置表头
            setTitle(workbook, sheet, data.getHead());
            //设置单元格并赋值
            setData(sheet, data.getData());
            //设置浏览器下载
            setBrowser(request, response, workbook, data.getFileName());
            log.info("导出解析成功!");
        } catch (Exception e) {
            log.info("导出解析失败!");
            e.printStackTrace();
        }
    }

    /**
     * 设置表头
     *
     * @param workbook
     * @param sheet
     * @param heads
     */
    private static void setTitle(HSSFWorkbook workbook, HSSFSheet sheet, List<String> heads) {
        try {
            HSSFRow row = sheet.createRow(0);
            //设置列宽，setColumnWidth的第二个参数要乘以256，这个参数的单位是1/256个字符宽度
            for (int i = 0; i <= heads.size(); i++) {
                sheet.setColumnWidth(i, 15 * 256);
            }
            //设置为居中加粗,格式化时间格式
            HSSFCellStyle style = workbook.createCellStyle();
            HSSFFont font = workbook.createFont();
            font.setBold(true);
            style.setFont(font);
            style.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));
            //创建表头名称
            HSSFCell cell;
            for (int j = 0; j < heads.size(); j++) {
                cell = row.createCell(j);
                cell.setCellValue(heads.get(j));
                cell.setCellStyle(style);
            }
        } catch (Exception e) {
            log.info("导出时设置表头失败！");
            e.printStackTrace();
        }
    }

    /**
     * 表格赋值
     *
     * @param sheet
     * @param data
     */
    private static void setData(HSSFSheet sheet, List<List<String>> data) {
        try {
            int rowNum = 1;
            for (int i = 0; i < data.size(); i++) {
                HSSFRow row = sheet.createRow(rowNum);
                for (int j = 0; j < data.get(i).size(); j++) {
                    row.createCell(j).setCellValue(data.get(i).get(j));
                }
                rowNum++;
            }
            log.info("表格赋值成功！");
        } catch (Exception e) {
            log.info("表格赋值失败！");
            e.printStackTrace();
        }
    }

    /**
     * 使用浏览器下载
     *
     * @param response
     * @param workbook
     * @param fileName
     */
    private static void setBrowser(HttpServletRequest request, HttpServletResponse response, HSSFWorkbook workbook, String fileName) {
        try {
            //清空response
            response.reset();
            //设置response的Header  前端乱码需要转码 fileName = window.decodeURI(res.headers['content-disposition'].split('=')[1], "UTF-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + FileUtils.setFileDownloadHeader(request, fileName));
            response.setContentType("application/vnd.ms-excel;charset=gb2312");
            OutputStream os = new BufferedOutputStream(response.getOutputStream());
            //将excel写入到输出流中
            workbook.write(os);
            os.flush();
            os.close();
            log.info("设置浏览器下载成功！");
        } catch (Exception e) {
            log.info("设置浏览器下载失败！");
            e.printStackTrace();
        }

    }


    /**
     * 导入
     *
     * @param fileName
     * @return
     */
    public static List<Object[]> importExcel(String fileName) {
        log.info("导入解析开始，fileName:{}", fileName);
        try {
            List<Object[]> list = new ArrayList<>();
            InputStream inputStream = new FileInputStream(fileName);
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            //获取sheet的行数
            int rows = sheet.getPhysicalNumberOfRows();
            for (int i = 0; i < rows; i++) {
                //过滤表头行
                if (i == 0) {
                    continue;
                }
                //获取当前行的数据
                Row row = sheet.getRow(i);
                Object[] objects = new Object[row.getPhysicalNumberOfCells()];
                int index = 0;
                for (Cell cell : row) {
                    if (cell.getCellType().equals(NUMERIC)) {
                        objects[index] = (int) cell.getNumericCellValue();
                    }
                    if (cell.getCellType().equals(STRING)) {
                        objects[index] = cell.getStringCellValue();
                    }
                    if (cell.getCellType().equals(BOOLEAN)) {
                        objects[index] = cell.getBooleanCellValue();
                    }
                    if (cell.getCellType().equals(ERROR)) {
                        objects[index] = cell.getErrorCellValue();
                    }
                    index++;
                }
                list.add(objects);
            }
            log.info("导入文件解析成功！");
            return list;
        } catch (Exception e) {
            log.info("导入文件解析失败！");
            e.printStackTrace();
        }
        return null;
    }

}
