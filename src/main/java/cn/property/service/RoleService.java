package cn.property.service;

import cn.property.model.SysRole;
import com.github.pagehelper.PageInfo;

public interface RoleService {
    SysRole selectPermissionCodeById(String id);

    PageInfo<SysRole> selectRoleList(SysRole sysRole,Integer page, Integer limit);

    void insertRole(SysRole sysRole);

    void deleteRoleById(String id);

    SysRole selectRoleAndPermissionById(String id);

    void updateRoleById(SysRole sysRole);
}
