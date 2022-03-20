package cn.property.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * (Complaint)实体类
 *
 * @author makejava
 * @since 2022-03-19 14:25:22
 */
@Data
public class Complaint implements Serializable {
    private static final long serialVersionUID = -28726805785707629L;
    
    private String id;
    
    private String userId;
    
    private String complaintTitle;
    
    private String complaintContent;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date complaintTime;
    
    private Integer isDel;

    private String userName;


}

