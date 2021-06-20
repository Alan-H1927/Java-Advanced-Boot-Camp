-- javacourse.javacourse_order definition

CREATE TABLE `javacourse_order` (
                                    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增物理主键',
                                    `order_id` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '订单id',
                                    `order_status` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '订单状态（未付款,已付款,已发货,已签收,退货申请,退货中,已退货,取消交易）',
                                    `order_goods_count` int(11) NOT NULL COMMENT '订单商品总数量',
                                    `order_goods_price` decimal(10,0) NOT NULL COMMENT '订单商品总价格',
                                    `order_actual_pay_amount` decimal(10,0) DEFAULT NULL COMMENT '订单实际付款总金额',
                                    `order_actual_pay_person` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '订单实际支付人（会员ID）',
                                    `order_actual_pay_type` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '订单实际支付方式（1，支付宝；2，银行卡；3，信用卡；4，微信）',
                                    `order_actual_pay_time` datetime DEFAULT NULL COMMENT '订单实际支付时间',
                                    `order_remark` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '订单备注',
                                    `order_user_id` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建订单的用户id',
                                    `create_person` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建人',
                                    `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                    `create_machine` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创建机器IP',
                                    `update_person` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '修改人',
                                    `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
                                    `update_machine` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '修改机器IP',
                                    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;