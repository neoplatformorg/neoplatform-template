package com.neo.platform.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("sys_user")
public class SysUser {
    
    @TableId(type = IdType.AUTO)
    private Long id;
    
    private String username;
    
    private String password;
    
    private String nickname;
    
    private String email;
    
    private String phone;
    
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    
    @TableLogic
    private Integer deleted;
    
    private Integer status;
    
    // 领域方法
    public boolean isEnabled() {
        return status != null && status == 1;
    }
    
    public boolean isDeleted() {
        return deleted != null && deleted == 1;
    }
    
    public void disable() {
        this.status = 0;
        this.updateTime = LocalDateTime.now();
    }
    
    public void enable() {
        this.status = 1;
        this.updateTime = LocalDateTime.now();
    }
    
    public void updatePassword(String newPassword) {
        this.password = newPassword;
        this.updateTime = LocalDateTime.now();
    }
    
    public void updateProfile(String nickname, String email, String phone) {
        this.nickname = nickname;
        this.email = email;
        this.phone = phone;
        this.updateTime = LocalDateTime.now();
    }
}