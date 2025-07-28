-- 创建用户表
CREATE TABLE IF NOT EXISTS `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 插入测试数据
INSERT INTO `user` (`username`, `password`, `email`) VALUES
('admin', '123456', 'admin@example.com'),
('user1', 'password1', 'user1@example.com'),
('user2', 'password2', 'user2@example.com')
ON DUPLICATE KEY UPDATE `username`=`username`;

-- 创建角色表
CREATE TABLE IF NOT EXISTS `Demo`.`role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) NOT NULL,
  `user_id` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_name` (`role_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 插入角色测试数据
-- 插入测试数据
INSERT INTO `Demo`.`role` (`role_name`, `user_id`) VALUES
   ('超级管理员', '1'),
   ('运营', '1'),
   ('研发', '1')
    ON DUPLICATE KEY UPDATE `role_name`=`role_name`;

DROP TABLE `Demo`.`role`;

