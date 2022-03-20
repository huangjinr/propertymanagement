package cn.property.entity;

import java.io.Serializable;

/**
 * (Carport)实体类
 *
 * @author makejava
 * @since 2022-03-20 13:59:53
 */
public class Carport implements Serializable {
    private static final long serialVersionUID = -84822911684855356L;
    
    private String id;
    
    private String carportNum;
    
    private String userId;
    
    private String buildingId;
    
    private Integer isUse;
    
    private Integer isDel;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCarportNum() {
        return carportNum;
    }

    public void setCarportNum(String carportNum) {
        this.carportNum = carportNum;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }

    public Integer getIsUse() {
        return isUse;
    }

    public void setIsUse(Integer isUse) {
        this.isUse = isUse;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

}

