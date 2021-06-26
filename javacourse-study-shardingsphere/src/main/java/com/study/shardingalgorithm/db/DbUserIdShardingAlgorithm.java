package com.study.shardingalgorithm.db;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

/**
 * //TODO 添加类/接口功能描述
 *
 * @author me-ht
 * @date 2021-06-26
 */
public class DbUserIdShardingAlgorithm implements PreciseShardingAlgorithm<String> {

    public DbUserIdShardingAlgorithm() {
    }

    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<String> preciseShardingValue) {
        System.out.println("DbUserIdShardingAlgorithm begin");
        int value = Integer.parseInt(preciseShardingValue.getValue());
        return preciseShardingValue.getLogicTableName() + "_" + value;
    }
}
