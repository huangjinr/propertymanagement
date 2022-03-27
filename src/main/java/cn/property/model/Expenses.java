package cn.property.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * (Expenses)实体类
 *
 * @author makejava
 * @since 2022-03-20 23:01:00
 */
@Data
public class Expenses implements Serializable {
    private static final long serialVersionUID = -48478347134744188L;
    
    private String id;
    
    private Double expensesMoney;
    
    private String userId;
    
    private String expensesInfo;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
    private Date expensesTime;
    
    private Integer expensesStatus;
    
    private Integer isDel;

    private String userName;



}

