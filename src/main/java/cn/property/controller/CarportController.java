package cn.property.controller;


import cn.property.model.Carport;
import cn.property.service.CarportService;
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
@RequestMapping("carport")
public class CarportController {

    @Autowired
    private CarportService carportService;


    @RequestMapping("index")
    private String index(){
        return "views/carport/list.html";
    }


    @RequestMapping(value = "selectCarportList", method = {RequestMethod.GET})
    @ResponseBody
    public ModelMap selectCarportList(Carport carport,Integer page,Integer limit) {
        PageInfo<Carport> carportList = carportService.selectCarportList(carport,page,limit);
        return ReturnUtil.Success("查询成功", carportList.getList(), carportList.getTotal());
    }

    @PostMapping("insertCarport")
    @ResponseBody
    public ModelMap insertCarport(Carport carport) {
        Carport c = carportService.selectCarportByCarportNum(carport.getCarportNum());
        if (c != null){
            return ReturnUtil.Error("车位号已存在，不能添加");
        }
        try {
            carportService.insertCarport(carport);
            return ReturnUtil.Success("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnUtil.Error("操作失败");
        }
    }

    @GetMapping("deleteCarportById")
    @ResponseBody
    public ModelMap deleteCarportById(String id) {
        try {
            carportService.deleteCarportById(id);
            return ReturnUtil.Success("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnUtil.Error("删除失败");
        }
    }

    @PostMapping("updateCarportById")
    @ResponseBody
    public ModelMap updateCarportById(Carport carport) {

        try {
            carportService.updateCarportById(carport);
            return ReturnUtil.Success("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnUtil.Error("操作失败");
        }
    }

    @RequestMapping("updateCarportIndex")
    public String selectCarportById(String id, Model model) {
        Carport carport = carportService.selectCarportById(id);
        model.addAttribute("carport", carport);
        return "views/carport/carportform";
    }

}

