package com.study.week6.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * //订单
 *
 * @author me-ht
 * @date 2021-06-14
 */
@Data
public class Order extends BaseEntity {
    /**
     * 订单id
     */
    private String orderId;
    /**
     * 订单类型
     */
    private String orderType;
    /**
     * 订单状态（未付款,已付款,已发货,已签收,退货申请,退货中,已退货,取消交易）
     */
    private String orderStatus;
    /**
     * 订单商品信息（商品ID的集合）
     */
    private String orderGoodsInfo;
    /**
     * 订单商品数量
     */
    private String orderGoodsCount;
    /**
     * 订单商品总价格
     */
    private BigDecimal orderGoodsPrice;
    /**
     * 订单优惠原因
     */
    private String orderDiscountReason;
    /**
     * 订单优惠金额
     */
    private BigDecimal orderDiscountAmount;
    /**
     * 订单实际付款金额
     */
    private BigDecimal orderActualPayAmount;
    /**
     * 订单实际支付人（会员ID）
     */
    private String orderActualPayPerson;
    /**
     * 订单实际支付方式（1，支付宝；2，银行卡；3，信用卡；4，微信）
     */
    private String orderActualPayType;
    /**
     * 订单实际支付时间
     */
    private Date orderActualPayTime;
    /**
     * 订单发货时间
     */
    private Date orderDeliveryGoodsTime;
    /**
     * 订单预期到达时间
     */
    private Date orderExpectArriveTime;
    /**
     * 订单期望配送时间
     */
    private Date orderExpectDeliveryTime;
    /**
     * 订单实际配送时间
     */
    private Date orderActualDeliveryTime;
    /**
     * 订单收货人
     */
    private String orderReceivePerson;
    /**
     * 订单收货人手机号
     */
    private String orderReceivePersonPhoneNumber;
    /**
     * 订单收货地址（地址表ID）
     */
    private String orderReceiveAddress;
    /**
     * 订单收货时间
     */
    private Date orderReceiveTime;
    /**
     * 订单运费
     */
    private BigDecimal orderTransportFee;
    /**
     * 订单物流编号（物流表ID）
     */
    private String orderLogisticsId;
    /**
     * 订单支付单号（支付号ID）
     */
    private String orderPayId;
    /**
     * 订单交易单号（交易号ID）
     */
    private String orderTransactionId;
    /**
     * 订单备注
     */
    private String orderRemark;
    /**
     * 创建订单的用户id
     */
    private String orderUserId;

}
