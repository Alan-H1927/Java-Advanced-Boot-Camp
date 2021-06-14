package com.study.week6.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * //商品
 *
 * @author me-ht
 * @date 2021-06-14
 */
@Data
public class Goods extends BaseEntity {
    /**
     * 商品id
     */
    private String goodsId;
    /**
     * 商品类型（1，食品；2，化妆品；等等）
     */
    private String goodsType;
    /**
     * 商品进货价格
     */
    private BigDecimal goodsPurchasePrice;
    /**
     * 商品销售价格
     */
    private BigDecimal goodsSellPrice;
    /**
     * 商品货架数量
     */
    private int goodsShelfCount;
    /**
     * 商品库存数量
     */
    private int goodsStoreCount;
    /**
     * 商品最新进货时间
     */
    private Date goodsLastPurchaseTime;
    /**
     * 商品最新进货数量
     */
    private int goodsLastPurchaseCount;
    /**
     * 商品最新进货人（进货人ID）
     */
    private String goodsLastPurchasePerson;
    /**
     * 商品最新供应商（供应商ID）
     */
    private String goodsLastSupplier;
}
