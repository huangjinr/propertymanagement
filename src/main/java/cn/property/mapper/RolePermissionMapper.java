package cn.property.mapper;

import cn.property.model.SysRolePermission;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface RolePermissionMapper {
    void insertRolePermission(SysRolePermission sysRolePermission);

    void deleteRolePermissionByRoleId(String id);

}
