package cn.property.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.property.mapper.PermissionMapper;
import cn.property.model.SysPermission;
import cn.property.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<SysPermission> selectPermissionList() {
        return permissionMapper.selectPermissionList();
    }

    @Override
    public void insertPermission(SysPermission sysPermission) {
        sysPermission.setId(IdUtil.simpleUUID());
        sysPermission.setIsDel(0);
        if (-1 == sysPermission.getPermissionType()){
            sysPermission.setParentId("-1");
        }
        permissionMapper.insertPermission(sysPermission);
    }

    @Override
    public SysPermission selectPermissionById(String id) {
        return permissionMapper.selectPermissionById(id);
    }

    @Override
    public String selectParentNameByParentName(String id) {
        return permissionMapper.selectParentNameByParentName(id);
    }

    @Override
    public void updatePermission(SysPermission sysPermission) {
        permissionMapper.updatePermission(sysPermission);
    }

    @Override
    public void deletePermissionById(String id) {
        permissionMapper.deletePermissionById(id);
    }


}
