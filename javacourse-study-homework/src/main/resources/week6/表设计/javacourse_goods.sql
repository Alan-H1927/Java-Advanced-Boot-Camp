-- javacourse.javacourse_goods definition

CREATE TABLE `javacourse_goods` (
                                    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增物理主键',
                                    `goods_id` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '商品id',
                                    `goods_name` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '商品名称',
                                    `goods_type` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '商品类型（1，食品；2，化妆品；等等）',
                                    `goods_description` varchar(256) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '商品描述',
                                    `create_person` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建人',
                                    `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                    `create_machine` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创建机器IP',
                                    `update_person` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '修改人',
                                    `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
                                    `update_machine` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '修改机器IP',
                                    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;