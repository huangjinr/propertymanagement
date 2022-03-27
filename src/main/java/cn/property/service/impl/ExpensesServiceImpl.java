package cn.property.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.property.mapper.ExpensesMapper;
import cn.property.mapper.UserMapper;
import cn.property.model.Expenses;
import cn.property.service.ExpensesService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.attribute.standard.PrinterURI;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * (Expenses)表服务实现类
 *
 * @author makejava
 * @since 2022-02-12 09:43:08
 */
@Service
public class ExpensesServiceImpl implements ExpensesService {

    @Autowired
    private ExpensesMapper expensesMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public Expenses selectExpensesById(String id) {
        return expensesMapper.selectExpensesById(id);
    }

    @Override
    public PageInfo<Expenses> selectExpensesList(Expenses expenses, Integer page, Integer limit) {
        Map<String, Object> map = new HashMap<>();
        if (expenses != null) {
            if (!StrUtil.hasEmpty(expenses.getUserName())) {
                map.put("userName", expenses.getUserName());
            }
        }
        PageHelper.startPage(page,limit);
        List<Expenses> expensesList = expensesMapper.selectExpensesList(map);
        PageInfo<Expenses> pageInfo = new PageInfo<Expenses>(expensesList);
        return pageInfo;
    }

    @Override
    public void insertExpenses(Expenses expenses) {
        expenses.setUserId(userMapper.selectUserByName(expenses.getUserName()).getId());
        expenses.setId(IdUtil.simpleUUID());
        expenses.setIsDel(0);
        expenses.setExpensesStatus(0);
        expensesMapper.insertExpenses(expenses);
    }

    @Override
    public void deleteExpensesById(String id) {
        expensesMapper.deleteExpensesById(id);
    }

    @Override
    public void updateExpensesById(Expenses expenses) {
        expensesMapper.updateExpensesById(expenses);
    }

    @Override
    public void expensesSuccessById(String id) {
        Expenses expenses = new Expenses();
        expenses.setId(id);
        expenses.setExpensesStatus(1);
        expenses.setExpensesTime(new Date());
        expensesMapper.updateExpensesById(expenses);
    }
}
