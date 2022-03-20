package cn.property.model;

import lombok.Data;

@Data
public class SysUserRole {

    private String id;
    private String userId;
    private String roleId;
    private Integer isDel;
}
