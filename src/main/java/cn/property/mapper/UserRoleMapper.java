package cn.property.mapper;

import cn.property.model.SysUserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserRoleMapper {
    void insertUserRole(SysUserRole sysUserRole);

    void updateRoleIdByUserId(SysUserRole sysUserRole);

    void deleteUserRoleByUserId(String id);

    void deleteByUserIds(@Param("ids") List<String> ids);
}
