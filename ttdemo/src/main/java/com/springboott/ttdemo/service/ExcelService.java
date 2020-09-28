package com.springboott.ttdemo.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ExcelService {

    Boolean exportExcel(HttpServletRequest request, HttpServletResponse response, String fileName, Integer pageNum, Integer pageSize);

    Boolean importExcel(String fileName);

}
