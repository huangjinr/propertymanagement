package cn.property.controller;


import cn.property.model.Building;
import cn.property.service.BuildingService;
import cn.property.utils.ReturnUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * (Department)表控制层
 *
 * @author makejava
 * @since 2022-02-12 09:42:37
 */
@Controller
@RequestMapping("building")
public class BuildingController {

    @Autowired
    private BuildingService buildingService;


    @RequestMapping("index")
    private String index(){
        return "views/building/list.html";
    }


    @RequestMapping(value = "selectBuildingList", method = {RequestMethod.GET})
    @ResponseBody
    public ModelMap selectBuildingList(Building building,Integer page,Integer limit) {
        PageInfo<Building> buildingList = buildingService.selectBuildingList(building,page,limit);
        return ReturnUtil.Success("查询成功", buildingList.getList(), buildingList.getTotal());
    }

    @PostMapping("insertBuilding")
    @ResponseBody
    public ModelMap insertBuilding(Building building) {
        try {
            buildingService.insertBuilding(building);
            return ReturnUtil.Success("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnUtil.Error("操作失败");
        }
    }

    @GetMapping("deleteBuildingById")
    @ResponseBody
    public ModelMap deleteBuildingById(String id) {
        try {
            buildingService.deleteBuildingById(id);
            return ReturnUtil.Success("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnUtil.Error("删除失败");
        }
    }

    @PostMapping("updateBuildingById")
    @ResponseBody
    public ModelMap updateBuildingById(Building building) {

        try {
            buildingService.updateBuildingById(building);
            return ReturnUtil.Success("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnUtil.Error("操作失败");
        }
    }

    @RequestMapping("updateBuildingIndex")
    public String selectBuildingById(String id, Model model) {
        Building building = buildingService.selectBuildingById(id);
        model.addAttribute("building", building);
        return "views/building/buildingform";
    }

}

