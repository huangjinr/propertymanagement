package cn.property.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * (HouseProperty)实体类
 *
 * @author makejava
 * @since 2022-03-20 23:46:08
 */
@Data
public class HouseProperty implements Serializable {
    private static final long serialVersionUID = 241819847253893250L;
    
    private String id;
    
    private String userId;
    
    private String propertyNum;
    
    private Double propertyArea;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date propertyBuytime;
    
    private Integer propertyYears;
    
    private Integer isDel;

    private String userName;


}

