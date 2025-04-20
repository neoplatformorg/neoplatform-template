package com.neo.platform.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "更新用户资料请求")
public class UpdateProfileRequest {
    @Schema(description = "昵称")
    private String nickname;
    
    @Schema(description = "邮箱")
    private String email;
    
    @Schema(description = "手机号")
    private String phone;
}