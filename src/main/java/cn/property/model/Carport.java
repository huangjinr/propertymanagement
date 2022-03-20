package cn.property.model;

import lombok.Data;

import java.io.Serializable;

/**
 * (Carport)实体类
 *
 * @author makejava
 * @since 2022-03-20 13:59:53
 */
@Data
public class Carport implements Serializable {
    private static final long serialVersionUID = -84822911684855356L;
    
    private String id;
    
    private String carportNum;
    
    private String userId;
    
    private String buildingId;
    
    private Integer isUse;
    
    private Integer isDel;

    private String userName;

    private String buildingNumber;




}

