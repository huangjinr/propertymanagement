package cn.property.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Maintain)实体类
 *
 * @author makejava
 * @since 2022-03-20 13:19:35
 */
public class Maintain implements Serializable {
    private static final long serialVersionUID = -53705667991495651L;
    
    private String id;
    
    private String userId;
    
    private String maintainInfo;
    
    private Integer maintainStatus;
    
    private Date applyTime;
    
    private Date maintainTime;
    
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

    public String getMaintainInfo() {
        return maintainInfo;
    }

    public void setMaintainInfo(String maintainInfo) {
        this.maintainInfo = maintainInfo;
    }

    public Integer getMaintainStatus() {
        return maintainStatus;
    }

    public void setMaintainStatus(Integer maintainStatus) {
        this.maintainStatus = maintainStatus;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public Date getMaintainTime() {
        return maintainTime;
    }

    public void setMaintainTime(Date maintainTime) {
        this.maintainTime = maintainTime;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

}

