package cn.property.mapper;

import cn.property.model.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface RoleMapper {
    SysRole selectPermissionCodeById(String id);

    String selectRoleIdByRoleType(Integer roleType);

    List<SysRole> selectRoleList(Map<String, Object> map);

    void insertRole(SysRole sysRole);

    void deleteRoleById(String id);

    SysRole selectRoleAndPermissionById(String id);

    void updateRoleById(SysRole sysRole);
}
