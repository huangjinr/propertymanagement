package cn.property.model;

import lombok.Data;

@Data
public class SysRolePermission {

    private String id;
    private String roleId;
    private String permissionId;
    private Integer isDel;

}
