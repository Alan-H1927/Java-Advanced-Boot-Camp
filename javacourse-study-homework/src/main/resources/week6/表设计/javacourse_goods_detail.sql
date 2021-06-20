-- javacourse.javacourse_goods definition

CREATE TABLE `javacourse_goods_detail` (
                                    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增物理主键',
                                    `goods_id` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '商品id',
                                    `goods_purchase_price` decimal(10,0) NOT NULL COMMENT '商品进货价格',
                                    `goods_sell_price` decimal(10,0) DEFAULT NULL COMMENT '商品销售价格',
                                    `goods_shelf_count` int(11) DEFAULT NULL COMMENT '商品货架数量',
                                    `goods_store_count` int(11) DEFAULT NULL COMMENT '商品库存数量',
                                    `goods_last_purchase_time` datetime NOT NULL COMMENT '商品最新进货时间',
                                    `goods_last_purchase_count` int(11) NOT NULL COMMENT '商品最新进货数量',
                                    `goods_last_purchase_person` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '商品最新进货人（进货人ID）',
                                    `goods_last_supplier` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '商品最新供应商（供应商ID）',
                                    `create_person` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建人',
                                    `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                    `create_machine` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创建机器IP',
                                    `update_person` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '修改人',
                                    `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
                                    `update_machine` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '修改机器IP',
                                    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;