package cn.property.service;


import cn.property.model.Expenses;
import com.github.pagehelper.PageInfo;

/**
 * (Department)表服务接口
 *
 * @author makejava
 * @since 2022-02-12 09:43:08
 */
public interface ExpensesService {

    Expenses selectExpensesById(String id);

    PageInfo<Expenses> selectExpensesList(Expenses expenses,Integer page,Integer limit);

    void insertExpenses(Expenses expenses);

    void deleteExpensesById(String id);

    void updateExpensesById(Expenses expenses);

    void expensesSuccessById(String id);
}
