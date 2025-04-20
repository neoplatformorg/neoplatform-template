package com.neo.platform.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.neo.platform.entity.SysUser;
import com.neo.platform.mapper.SysUserMapper;
import com.neo.platform.service.SysUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    
    @Override
    public <E extends IPage<SysUser>> E page(E page) {
        return lambdaQuery()
            .orderByDesc(SysUser::getId)
            .page(page);
    }
    
    @Override
    public SysUser findByUsername(String username) {
        return lambdaQuery().eq(SysUser::getUsername, username).one();
    }
    
    @Override
    @Transactional
    public void updateUserStatus(Long id, boolean enabled) {
        SysUser user = getById(id);
        if (user != null) {
            if (enabled) {
                user.enable();
            } else {
                user.disable();
            }
            updateById(user);
        }
    }
    
    @Override
    @Transactional
    public void updateUserProfile(Long id, String nickname, String email, String phone) {
        SysUser user = getById(id);
        if (user != null) {
            user.updateProfile(nickname, email, phone);
            updateById(user);
        }
    }
    
}