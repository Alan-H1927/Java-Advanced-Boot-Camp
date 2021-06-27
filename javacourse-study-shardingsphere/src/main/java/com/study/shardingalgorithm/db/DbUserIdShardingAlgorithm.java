package com.study.shardingalgorithm.db;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

/**
 * //TODO 添加类/接口功能描述
 *
 * @author me-ht
 * @date 2021-06-26
 */
public class DbUserIdShardingAlgorithm implements PreciseShardingAlgorithm<String> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public DbUserIdShardingAlgorithm() {
    }

    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<String> preciseShardingValue) {
        logger.info("DbUserIdShardingAlgorithm begin");
        int value = Integer.parseInt(preciseShardingValue.getValue());
        return "javacourse-" + (value % 2);
    }
}
