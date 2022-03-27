package cn.property.controller;


import cn.property.model.HouseProperty;
import cn.property.service.HousePropertyService;
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
@RequestMapping("houseProperty")
public class HousePropertyController {

    @Autowired
    private HousePropertyService housePropertyService;


    @RequestMapping("index")
    private String index(){
        return "views/houseProperty/list.html";
    }


    @RequestMapping(value = "selectHousePropertyList", method = {RequestMethod.GET})
    @ResponseBody
    public ModelMap selectHousePropertyList(HouseProperty houseProperty,Integer page,Integer limit) {
        PageInfo<HouseProperty> housePropertyList = housePropertyService.selectHousePropertyList(houseProperty,page,limit);
        return ReturnUtil.Success("查询成功", housePropertyList.getList(), housePropertyList.getTotal());
    }

    @PostMapping("insertHouseProperty")
    @ResponseBody
    public ModelMap insertHouseProperty(HouseProperty houseProperty) {
        try {
            housePropertyService.insertHouseProperty(houseProperty);
            return ReturnUtil.Success("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnUtil.Error("操作失败");
        }
    }

    @GetMapping("deleteHousePropertyById")
    @ResponseBody
    public ModelMap deleteHousePropertyById(String id) {
        try {
            housePropertyService.deleteHousePropertyById(id);
            return ReturnUtil.Success("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnUtil.Error("删除失败");
        }
    }

    @PostMapping("updateHousePropertyById")
    @ResponseBody
    public ModelMap updateHousePropertyById(HouseProperty houseProperty) {

        try {
            housePropertyService.updateHousePropertyById(houseProperty);
            return ReturnUtil.Success("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnUtil.Error("操作失败");
        }
    }

    @RequestMapping("updateHousePropertyIndex")
    public String selectHousePropertyById(String id, Model model) {
        HouseProperty houseProperty = housePropertyService.selectHousePropertyById(id);
        model.addAttribute("houseProperty", houseProperty);
        return "views/houseProperty/housePropertyform";
    }

}

