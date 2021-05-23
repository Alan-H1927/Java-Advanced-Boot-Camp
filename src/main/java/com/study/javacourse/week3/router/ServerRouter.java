package com.study.javacourse.week3.router;

import com.study.javacourse.week3.bean.UrlBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Random;

/**
 * //TODO 添加类/接口功能描述
 *
 * @author me-ht
 * @date 2021-05-23
 */
public class ServerRouter implements Router {

    private static final Logger logger = LoggerFactory.getLogger(ServerRouter.class);

    @Override
    public UrlBean route(List<UrlBean> urls) {
        logger.info("begin route");
        int size = urls.size();
        Random random = new Random(System.currentTimeMillis());
        UrlBean url = urls.get(random.nextInt(size));
        logger.info("route UrlBean is {}", url);
        return url;
    }
}
