package cn.property.entity;

import java.io.Serializable;

/**
 * (Expenses)实体类
 *
 * @author makejava
 * @since 2022-03-20 23:01:00
 */
public class Expenses implements Serializable {
    private static final long serialVersionUID = -48478347134744188L;
    
    private String id;
    
    private Double expensesMoney;
    
    private String userId;
    
    private String expensesInfo;
    
    private Integer expensesStatus;
    
    private Integer isDel;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getExpensesMoney() {
        return expensesMoney;
    }

    public void setExpensesMoney(Double expensesMoney) {
        this.expensesMoney = expensesMoney;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getExpensesInfo() {
        return expensesInfo;
    }

    public void setExpensesInfo(String expensesInfo) {
        this.expensesInfo = expensesInfo;
    }

    public Integer getExpensesStatus() {
        return expensesStatus;
    }

    public void setExpensesStatus(Integer expensesStatus) {
        this.expensesStatus = expensesStatus;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

}

