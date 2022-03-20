package cn.property.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * (Notice)实体类
 *
 * @author makejava
 * @since 2022-03-19 15:28:51
 */
@Data
public class Notice implements Serializable {
    private static final long serialVersionUID = -38620339976630630L;
    
    private String id;
    
    private String noticeTitle;
    
    private String noticeContent;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date noticeTime;
    
    private Integer isDel;




}

