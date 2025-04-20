package com.neo.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neo.platform.entity.SysUser;

public interface SysUserService extends IService<SysUser> {
    SysUser findByUsername(String username);
    void updateUserStatus(Long id, boolean enabled);
    void updateUserProfile(Long id, String nickname, String email, String phone);
}