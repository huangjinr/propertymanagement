package cn.property.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.property.mapper.RoleMapper;
import cn.property.mapper.UserMapper;
import cn.property.mapper.UserRoleMapper;
import cn.property.model.SysUser;
import cn.property.model.SysUserRole;
import cn.property.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;


    @Override
    public SysUser selectUserByUsername(String username) {
        return userMapper.selectUserByUsername(username);
    }

    @Override
    public PageInfo selectUserList(SysUser sysUser, Integer page, Integer limit) {
        Map<String, Object> map = new HashMap<>();
        if (sysUser != null) {
            if (!StrUtil.hasEmpty(sysUser.getUsername())) {
                map.put("username", sysUser.getUsername());
            }
            if (!StrUtil.hasEmpty(sysUser.getPhone())) {
                map.put("phone", sysUser.getPhone());
            }
            if (!StrUtil.hasEmpty(sysUser.getName())) {
                map.put("name", sysUser.getName());
            }
        }
        PageHelper.startPage(page,limit);
        List<SysUser> sysUserList = userMapper.selectUserList(map);
        PageInfo<SysUser> pageInfo = new PageInfo<SysUser>(sysUserList);
        return pageInfo;
    }

    @Override
    @Transactional
    public void insertUser(SysUser user) {
        String userId = IdUtil.simpleUUID();
        user.setId(userId);
        user.setIsDel(0);
        user.setJoinDate(new Date());
        user.setPassword(passwordEncoder.encode("123456"));
        String roleId = roleMapper.selectRoleIdByRoleType(user.getSysRole().getRoleType());
        SysUserRole sysUserRole = new SysUserRole();
        sysUserRole.setId(IdUtil.simpleUUID());
        sysUserRole.setUserId(userId);
        sysUserRole.setRoleId(roleId);
        sysUserRole.setIsDel(0);
        userRoleMapper.insertUserRole(sysUserRole);
        userMapper.insertUser(user);
    }

    @Override
    public SysUser selectUserById(String id) {
        return userMapper.selectUserById(id);
    }

    @Override
    @Transactional
    public void updateUser(SysUser sysUser) {
        String roleId = roleMapper.selectRoleIdByRoleType(sysUser.getSysRole().getRoleType());
        SysUserRole sysUserRole= new SysUserRole();
        sysUserRole.setUserId(sysUser.getId());
        sysUserRole.setRoleId(roleId);
        userRoleMapper.updateRoleIdByUserId(sysUserRole);
        userMapper.updateUser(sysUser);
    }

    @Override
    public void deleteUserById(String id) {
        userRoleMapper.deleteUserRoleByUserId(id);
        userMapper.deleteUserById(id);
    }

    @Override
    public void deleteByIds(List<String> ids) {
        userMapper.deleteByIds(ids);
        userRoleMapper.deleteByUserIds(ids);
    }
}
