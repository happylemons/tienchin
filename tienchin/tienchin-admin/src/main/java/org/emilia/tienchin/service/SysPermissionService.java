package org.emilia.tienchin.service;


import com.baomidou.mybatisplus.extension.service.IService;

public interface SysPermissionService extends IService<SysPermissionService> {
    Object hasPermission(Long userId);
}
