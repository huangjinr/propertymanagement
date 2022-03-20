package cn.property.service;

import cn.property.model.SysPermission;

import java.util.List;

public interface PermissionService {
    List<SysPermission> selectPermissionList();

    void insertPermission(SysPermission sysPermission);

    SysPermission selectPermissionById(String id);

    String selectParentNameByParentName(String id);

    void updatePermission(SysPermission sysPermission);

    void deletePermissionById(String id);
}
