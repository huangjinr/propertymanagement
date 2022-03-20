package cn.property.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.property.mapper.RoleMapper;
import cn.property.mapper.RolePermissionMapper;
import cn.property.model.SysRole;
import cn.property.model.SysRolePermission;
import cn.property.model.SysUser;
import cn.property.service.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public SysRole selectPermissionCodeById(String id) {
        return roleMapper.selectPermissionCodeById(id);
    }

    @Override
    public PageInfo<SysRole> selectRoleList(SysRole sysRole,Integer page, Integer limit) {
        Map<String, Object> map = new HashMap<>();
        if (sysRole != null) {
            if (!StrUtil.hasEmpty(sysRole.getRoleName())) {
                map.put("roleName", sysRole.getRoleName());
            }
        }
        PageHelper.startPage(page,limit);
        List<SysRole> sysRoleList = roleMapper.selectRoleList(map);
        PageInfo<SysRole> pageInfo = new PageInfo<SysRole>(sysRoleList);
        return pageInfo;

    }

    @Override
    @Transactional
    public void insertRole(SysRole sysRole) {
        String roleId = IdUtil.simpleUUID();
        sysRole.setId(roleId);
        sysRole.setIsDel(0);

        if (!StrUtil.hasEmpty(sysRole.getSysPermissionIds())) {
            //用逗号将字符串分开，得到字符串数组
            String sysPermissionIds = sysRole.getSysPermissionIds();
            String[] strs = sysPermissionIds.split(",");
            //将字符串数组转换成集合list
            List<String> idList = Arrays.asList(strs);
            for (String id : idList) {
                SysRolePermission sysRolePermission = new SysRolePermission();
                sysRolePermission.setId(IdUtil.simpleUUID());
                sysRolePermission.setRoleId(sysRole.getId());
                sysRolePermission.setPermissionId(id);
                sysRolePermission.setIsDel(0);
                rolePermissionMapper.insertRolePermission(sysRolePermission);
            }
        }
        roleMapper.insertRole(sysRole);
    }

    @Override
    @Transactional
    public void deleteRoleById(String id) {
        rolePermissionMapper.deleteRolePermissionByRoleId(id);
        roleMapper.deleteRoleById(id);
    }

    @Override
    public SysRole selectRoleAndPermissionById(String id) {
        return roleMapper.selectRoleAndPermissionById(id);
    }

    @Override
    @Transactional
    public void updateRoleById(SysRole sysRole) {
        rolePermissionMapper.deleteRolePermissionByRoleId(sysRole.getId());
        if (!StrUtil.hasEmpty(sysRole.getSysPermissionIds())) {
            //用逗号将字符串分开，得到字符串数组
            String sysPermissionIds = sysRole.getSysPermissionIds();
            String[] strs = sysPermissionIds.split(",");
            //将字符串数组转换成集合list
            List<String> idList = Arrays.asList(strs);
            for (String id : idList) {
                SysRolePermission sysRolePermission = new SysRolePermission();
                sysRolePermission.setId(IdUtil.simpleUUID());
                sysRolePermission.setRoleId(sysRole.getId());
                sysRolePermission.setPermissionId(id);
                sysRolePermission.setIsDel(0);
                rolePermissionMapper.insertRolePermission(sysRolePermission);
            }
        }
        roleMapper.updateRoleById(sysRole);
    }


}
