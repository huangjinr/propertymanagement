package cn.property.service;

import cn.property.model.SysUser;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface UserService {
    SysUser selectUserByUsername(String username);

    PageInfo<SysUser> selectUserList(SysUser sysUser, Integer page, Integer limit);

    void insertUser(SysUser sysUser);

    SysUser selectUserById(String id);

    void updateUser(SysUser sysUser);

    void deleteUserById(String id);

    void deleteByIds(List<String> ids);
}
