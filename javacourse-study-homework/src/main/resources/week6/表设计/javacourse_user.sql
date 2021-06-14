-- javacourse.javacourse_user definition

CREATE TABLE `javacourse_user` (
                                   `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增物理主键',
                                   `user_id` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户id',
                                   `user_username` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户账号',
                                   `user_password` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户密码，需要加密',
                                   `user_name` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户名称',
                                   `user_sex` char(1) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户性别（0，女；1，男）',
                                   `user_age` int(3) DEFAULT NULL COMMENT '用户年龄',
                                   `user_email` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户邮箱',
                                   `user_phone_number` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户手机号，需要加密',
                                   `user_id_card` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户身份证号，需要加密',
                                   `user_status` char(1) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '状态（1，正常;2，冻结）',
                                   `create_person` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '创建人',
                                   `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                   `create_machine` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '创建机器IP',
                                   `update_person` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '修改人',
                                   `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
                                   `update_machine` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '修改机器IP',
                                   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;