package cn.property.mapper;


import cn.property.model.Expenses;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface ExpensesMapper {


    Expenses selectExpensesById(String id);

    List<Expenses> selectExpensesList(Map<String, Object> map);

    void insertExpenses(Expenses expenses);

    void deleteExpensesById(String id);

    void updateExpensesById(Expenses expenses);

}
