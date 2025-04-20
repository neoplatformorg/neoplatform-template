-- 插入初始数据
INSERT INTO `sys_user` (`username`, `password`, `nickname`, `email`, `phone`, `create_time`, `update_time`, `status`)
VALUES 
('admin', '123456', '管理员', 'admin@neo.com', '13800138000', NOW(), NOW(), 1),
('test', '123456', '测试用户', 'test@neo.com', '13800138001', NOW(), NOW(), 1);