package cn.property.model;

import lombok.Data;

import java.io.Serializable;

/**
 * (Building)实体类
 *
 * @author makejava
 * @since 2022-03-18 21:15:32
 */
@Data
public class Building {

    private String id;
    /**
     * 几栋
     */
    private String buildingNumber;
    
    private String buildingUnit;
    
    private Integer isDel;



}

