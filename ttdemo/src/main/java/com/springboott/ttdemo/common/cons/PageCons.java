package com.springboott.ttdemo.common.cons;

/**
 * PAGE 常量
 *
 * @author Caratacus
 */
public interface PageCons {

    /**
     * 页数
     */
    String PAGE_PAGE = "cursor";
    /**
     * 分页大小
     */
    String PAGE_ROWS = "limit";
    /**
     * 排序字段 ASC
     */
    String PAGE_ASCS = "ascs";
    /**
     * 排序字段 DESC
     */
    String PAGE_DESCS = "descs";
    /**
     * 查询总数
     */
    String SEARCH_COUNT = "searchCount";
    /**
     * 排序方式 SORT
     */
    String PAGE_SORT = "sort";
    /**
     * 默认每页条目20,最大条目数100
     */
    int DEFAULT_LIMIT = 20;
    int MAX_LIMIT = 100;

}
