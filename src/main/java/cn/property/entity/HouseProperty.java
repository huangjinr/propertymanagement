package cn.property.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (HouseProperty)实体类
 *
 * @author makejava
 * @since 2022-03-20 23:46:08
 */
public class HouseProperty implements Serializable {
    private static final long serialVersionUID = 241819847253893250L;
    
    private String id;
    
    private String userId;
    
    private String propertyNum;
    
    private Double propertyArea;
    
    private Date propertyBuytime;
    
    private Integer propertyYears;
    
    private Integer isDel;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPropertyNum() {
        return propertyNum;
    }

    public void setPropertyNum(String propertyNum) {
        this.propertyNum = propertyNum;
    }

    public Double getPropertyArea() {
        return propertyArea;
    }

    public void setPropertyArea(Double propertyArea) {
        this.propertyArea = propertyArea;
    }

    public Date getPropertyBuytime() {
        return propertyBuytime;
    }

    public void setPropertyBuytime(Date propertyBuytime) {
        this.propertyBuytime = propertyBuytime;
    }

    public Integer getPropertyYears() {
        return propertyYears;
    }

    public void setPropertyYears(Integer propertyYears) {
        this.propertyYears = propertyYears;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

}

