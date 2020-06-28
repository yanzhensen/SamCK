package com.springboott.ttdemo.config.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.springboott.ttdemo.config.pageConfig.PageCons;
import com.springboott.ttdemo.config.response.AntiSQLFilter;
import com.springboott.ttdemo.config.response.ApiResponses;
import com.springboott.ttdemo.util.TypeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * SuperController
 *
 * @author Sam
 */
public class SuperController {

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected HttpServletResponse response;

    @Autowired
    protected HttpSession session;

    /**
     * 成功返回
     *
     * @param object
     * @return
     */
    public <T> ApiResponses<T> success(T object) {
        return ApiResponses.<T>success(response, object);
    }

    /**
     * 成功返回
     * authorization
     *
     * @return
     */
    public ApiResponses<Void> success() {
        return success(HttpStatus.OK);
    }

    /**
     * 成功返回
     *
     * @param status
     * @param object
     * @return
     */
    public <T> ApiResponses<T> success(HttpStatus status, T object) {
        return ApiResponses.<T>success(response, status, object);
    }


    /**
     * 成功返回
     *
     * @param status
     * @return
     */
    public ApiResponses<Void> success(HttpStatus status) {
        return ApiResponses.<Void>success(response, status);
    }

    /**
     * 获取分页对象
     *
     * @return
     */
    protected <T> Page<T> getPage() {
        return getPage(false);
    }

    /**
     * 获取分页对象
     *
     * @param openSort
     * @return
     */
    protected <T> Page<T> getPage(boolean openSort) {
        int index = 1;
        // 页数
        Integer cursor = TypeUtils.castToInt(request.getParameter(PageCons.PAGE_PAGE), index);
        // 分页大小
        Integer limit = TypeUtils.castToInt(request.getParameter(PageCons.PAGE_ROWS), PageCons.DEFAULT_LIMIT);
        // 是否查询分页
        Boolean searchCount = TypeUtils.castToBoolean(request.getParameter(PageCons.SEARCH_COUNT), true);
        limit = limit > PageCons.MAX_LIMIT ? PageCons.MAX_LIMIT : limit;
        Page<T> page = new Page<>(cursor, limit, searchCount);
        if (openSort) {
            page.setAsc(getParameterSafeValues(PageCons.PAGE_ASCS));
            page.setDesc(getParameterSafeValues(PageCons.PAGE_DESCS));
        }
        return page;
    }

    /**
     * 获取升序
     *
     * @return
     */
    protected String getAscs() {
        return TypeUtils.castToString(request.getParameter(PageCons.PAGE_ASCS));
    }

    /**
     * 获取倒序
     *
     * @return
     */
    protected String getSort() {
        return TypeUtils.castToString(request.getParameter(PageCons.PAGE_SORT));
    }

    /**
     * 获取倒序
     *
     * @return
     */
    protected String getDescs() {
        return TypeUtils.castToString(request.getParameter(PageCons.PAGE_DESCS));
    }

    /**
     * 获取页数
     *
     * @return
     */
    protected Integer getCursor() {
        int index = 1;
        return TypeUtils.castToInt(request.getParameter(PageCons.PAGE_PAGE), index);
    }

    /**
     * 获取分页大小
     *
     * @return
     */
    protected Integer getLimit() {
        return TypeUtils.castToInt(request.getParameter(PageCons.PAGE_ROWS), PageCons.DEFAULT_LIMIT);
    }

    /**
     * 获取安全参数(SQL ORDER BY 过滤)
     *
     * @param parameter
     * @return
     */
    protected String[] getParameterSafeValues(String parameter) {
        return AntiSQLFilter.getSafeValues(request.getParameterValues(parameter));
    }

}
