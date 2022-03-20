package cn.property.mapper;

import cn.property.model.SysPermission;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface PermissionMapper {
    List<SysPermission> selectPermissionList();

    void insertPermission(SysPermission sysPermission);

    SysPermission selectPermissionById(String id);

    String selectParentNameByParentName(String id);

    void updatePermission(SysPermission sysPermission);

    void deletePermissionById(String id);
}
