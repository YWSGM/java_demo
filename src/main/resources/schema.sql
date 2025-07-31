-- 创建用户表
CREATE TABLE IF NOT EXISTS java_demo.`users` (
    `user_id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户ID',
    `username` VARCHAR(50) NOT NULL COMMENT '用户名',
    `password` VARCHAR(255) NOT NULL COMMENT '密码(加密存储)',
    `email` VARCHAR(100) NOT NULL COMMENT '电子邮箱',
    `phone` VARCHAR(20) DEFAULT NULL COMMENT '电话号码',
    `status` TINYINT(1) NOT NULL DEFAULT 1 COMMENT '状态(1:启用,0:禁用)',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`user_id`),
    UNIQUE KEY `idx_username` (`username`),
    UNIQUE KEY `idx_email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- 插入测试数据
INSERT INTO java_demo.`users` (`username`, `password`, `email`) VALUES
('admin', '123456', 'admin@example.com'),
('user1', 'password1', 'user1@example.com'),
('user2', 'password2', 'user2@example.com')
ON DUPLICATE KEY UPDATE `username`=`username`;

-- 创建角色表
CREATE TABLE IF NOT EXISTS java_demo.`roles` (
    `role_id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '角色ID',
    `role_name` VARCHAR(50) NOT NULL COMMENT '角色名称',
    `role_desc` VARCHAR(200) DEFAULT NULL COMMENT '角色描述',
    `status` TINYINT(1) NOT NULL DEFAULT 1 COMMENT '状态(1:启用,0:禁用)',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`role_id`),
    UNIQUE KEY `idx_role_name` (`role_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

-- 插入角色测试数据
-- 插入测试数据
INSERT INTO java_demo.`roles` (`role_name`, `role_desc`) VALUES
    ('超级管理员', '超级管理员'),
    ('运营', '运营'),
    ('研发', '研发')
    ON DUPLICATE KEY UPDATE `role_name`=`role_name`;

DROP TABLE java_demo.`roles`;

CREATE TABLE IF NOT EXISTS java_demo.`user_role` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '关联ID',
    `user_id` INT UNSIGNED NOT NULL COMMENT '用户ID',
    `role_id` INT UNSIGNED NOT NULL COMMENT '角色ID',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_user_role` (`user_id`,`role_id`),
    KEY `idx_role_id` (`role_id`),
    CONSTRAINT `fk_ur_user_id` FOREIGN KEY (`user_id`) REFERENCES java_demo.`users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `fk_ur_role_id` FOREIGN KEY (`role_id`) REFERENCES java_demo.`roles` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户角色关联表';

INSERT INTO java_demo.user_role (user_id, role_id) VALUES (1, 2);

#查询用户所有角色
SELECT u.username, r.role_name
FROM java_demo.users u
    JOIN java_demo.user_role ur ON u.user_id = ur.user_id
    JOIN java_demo.roles r ON ur.role_id = r.role_id
WHERE u.user_id = 1;

#查询拥有特定角色的所有用户
SELECT u.username, u.email
FROM java_demo.users u
    JOIN java_demo.user_role ur ON u.user_id = ur.user_id
WHERE ur.role_id = 2;

CREATE DATABASE java_demo;

-- 创建API日志表
CREATE TABLE IF NOT EXISTS api_logs (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    log_id VARCHAR(50) NOT NULL COMMENT '日志ID',
    request_url VARCHAR(255) COMMENT '请求URL',
    request_method VARCHAR(10) COMMENT '请求方法',
    request_params LONGTEXT COMMENT '请求参数',
    response_data LONGTEXT COMMENT '响应数据',
    error_message LONGTEXT COMMENT '错误信息',
    status_code INT COMMENT 'HTTP状态码',
    execution_time BIGINT COMMENT '执行时间(毫秒)',
    create_time DATETIME COMMENT '创建时间',
    INDEX idx_log_id (log_id),
    INDEX idx_create_time (create_time)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='API请求日志表';

ALTER TABLE java_demo.roles
ADD COLUMN is_delete INT COMMENT '是否删除';
