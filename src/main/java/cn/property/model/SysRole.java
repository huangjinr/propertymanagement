package cn.property.model;

import lombok.Data;

import java.util.List;

@Data
public class SysRole {

    private String id;
    private String roleName;
    private String roleEnName;
    private Integer roleType;
    private Integer isDel;

    private String sysPermissionIds;

    private List<SysPermission> sysPermissionList;
}
