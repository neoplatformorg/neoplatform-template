package com.neo.platform.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.neo.platform.dto.CreateUserRequest;
import com.neo.platform.dto.UpdateProfileRequest;
import com.neo.platform.dto.UserDTO;
import com.neo.platform.entity.SysUser;
import com.neo.platform.service.SysUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "用户管理", description = "系统用户相关接口")
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class SysUserController {

    private final SysUserService userService;

    @Operation(summary = "获取用户列表")
    @GetMapping
    public IPage<UserDTO> list(@RequestParam(defaultValue = "1") int page,
                              @RequestParam(defaultValue = "10") int size) {
        IPage<SysUser> userPage = userService.page(new Page<>(page, size));
        return userPage.convert(this::convertToDTO);
    }

    @Operation(summary = "获取用户详情")
    @GetMapping("/{id}")
    public UserDTO getById(@PathVariable Long id) {
        return convertToDTO(userService.getById(id));
    }

    @Operation(summary = "创建用户")
    @PostMapping
    public UserDTO create(@RequestBody CreateUserRequest request) {
        SysUser user = new SysUser();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setNickname(request.getNickname());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        userService.save(user);
        return convertToDTO(user);
    }

    @Operation(summary = "更新用户状态")
    @PutMapping("/{id}/status")
    public UserDTO updateStatus(@PathVariable Long id, @RequestParam boolean enabled) {
        userService.updateUserStatus(id, enabled);
        return convertToDTO(userService.getById(id));
    }

    @Operation(summary = "更新用户资料")
    @PutMapping("/{id}/profile")
    public UserDTO updateProfile(@PathVariable Long id, @RequestBody UpdateProfileRequest request) {
        userService.updateUserProfile(id, request.getNickname(), request.getEmail(), request.getPhone());
        return convertToDTO(userService.getById(id));
    }

    @Operation(summary = "删除用户")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.removeById(id);
    }

    private UserDTO convertToDTO(SysUser user) {
        if (user == null) {
            return null;
        }
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setNickname(user.getNickname());
        dto.setEmail(user.getEmail());
        dto.setPhone(user.getPhone());
        dto.setStatus(user.getStatus());
        return dto;
    }
}