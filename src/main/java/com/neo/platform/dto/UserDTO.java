package com.neo.platform.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "用户信息")
public class UserDTO {
    @Schema(description = "用户ID")
    private Long id;
    
    @Schema(description = "用户名")
    private String username;
    
    @Schema(description = "昵称")
    private String nickname;
    
    @Schema(description = "邮箱")
    private String email;
    
    @Schema(description = "手机号")
    private String phone;
    
    @Schema(description = "状态")
    private Integer status;
}