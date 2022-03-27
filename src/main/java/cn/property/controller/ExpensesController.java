package cn.property.controller;


import cn.property.model.Expenses;
import cn.property.service.ExpensesService;
import cn.property.utils.ReturnUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * (Department)表控制层
 *
 * @author makejava
 * @since 2022-02-12 09:42:37
 */
@Controller
@RequestMapping("expenses")
public class ExpensesController {

    @Autowired
    private ExpensesService expensesService;


    @RequestMapping("index")
    private String index(){
        return "views/expenses/list.html";
    }


    @RequestMapping(value = "selectExpensesList", method = {RequestMethod.GET})
    @ResponseBody
    public ModelMap selectExpensesList(Expenses expenses,Integer page,Integer limit) {
        PageInfo<Expenses> expensesList = expensesService.selectExpensesList(expenses,page,limit);
        return ReturnUtil.Success("查询成功", expensesList.getList(), expensesList.getTotal());
    }

    @PostMapping("insertExpenses")
    @ResponseBody
    public ModelMap insertExpenses(Expenses expenses) {
        try {
            expensesService.insertExpenses(expenses);
            return ReturnUtil.Success("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnUtil.Error("操作失败");
        }
    }

    @GetMapping("deleteExpensesById")
    @ResponseBody
    public ModelMap deleteExpensesById(String id) {
        try {
            expensesService.deleteExpensesById(id);
            return ReturnUtil.Success("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnUtil.Error("删除失败");
        }
    }

    @PostMapping("updateExpensesById")
    @ResponseBody
    public ModelMap updateExpensesById(Expenses expenses) {

        try {
            expensesService.updateExpensesById(expenses);
            return ReturnUtil.Success("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnUtil.Error("操作失败");
        }
    }

    @RequestMapping("updateExpensesIndex")
    public String selectExpensesById(String id, Model model) {
        Expenses expenses = expensesService.selectExpensesById(id);
        model.addAttribute("expenses", expenses);
        return "views/expenses/expensesform";
    }

    @GetMapping("expensesSuccessById")
    @ResponseBody
    public ModelMap expensesSuccessById(String id) {
        try {
            expensesService.expensesSuccessById(id);
            return ReturnUtil.Success("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnUtil.Error("操作失败");
        }
    }

}

