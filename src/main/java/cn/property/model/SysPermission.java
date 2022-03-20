package cn.property.model;

import lombok.Data;

@Data
public class SysPermission {

    private String id;
    private String permissionName;
    private String permissionParentName;
    private String permissionCode;
    private Integer permissionType;
    private String permissionUrl;
    private String parentId;
    private Integer sort;
    private Integer isDel;
}
