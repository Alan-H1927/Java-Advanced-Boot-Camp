package com.study.javacourse.week3.router;

import com.study.javacourse.week3.bean.UrlBean;

import java.util.List;

/**
 * //TODO 添加类/接口功能描述
 *
 * @author me-ht
 * @date 2021-05-23
 */
public interface Router {

    /**
     * basic route
     * @param urls
     * @return
     */
    public UrlBean route(List<UrlBean> urls);
}
