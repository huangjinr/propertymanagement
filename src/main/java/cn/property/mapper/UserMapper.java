package cn.property.mapper;

import cn.property.model.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface UserMapper {
    SysUser selectUserByUsername(String username);

    List<SysUser> selectUserList(Map<String, Object> map);

    void insertUser(SysUser user);

    SysUser selectUserById(String id);

    void updateUser(SysUser sysUser);

    void deleteUserById(String id);

    void deleteByIds(@Param("ids") List<String> ids);

    SysUser selectUserByName(String userName);
}
