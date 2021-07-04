package com.study.impl;


import com.study.annotation.ProviderService;
import com.study.facade.GoodService;


/**
 * //TODO 添加类/接口功能描述
 *
 * @author me-ht
 * @date 2021-07-03
 */
@ProviderService(service = "com.study.facade.GoodService", group = "good", version = "1.0", weight = 2)
public class GoodServiceImpl implements GoodService {

    @Override
    public Integer count() {
        return 10;
    }
}
