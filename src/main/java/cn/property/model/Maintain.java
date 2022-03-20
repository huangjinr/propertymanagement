package cn.property.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * (Maintain)实体类
 *
 * @author makejava
 * @since 2022-03-20 13:19:35
 */
@Data
public class Maintain implements Serializable {
    private static final long serialVersionUID = -53705667991495651L;
    
    private String id;
    
    private String userId;
    
    private String maintainInfo;
    
    private Integer maintainStatus;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date applyTime;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date maintainTime;
    
    private Integer isDel;

    private String userName;




}

