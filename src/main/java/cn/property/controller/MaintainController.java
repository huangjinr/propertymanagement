package cn.property.controller;

import cn.property.model.Maintain;
import cn.property.model.SysUser;
import cn.property.service.MaintainService;
import cn.property.utils.ReturnUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Maintain)表控制层
 *
 * @author makejava
 * @since 2022-03-19 14:25:20
 */
@Controller
@RequestMapping("maintain")
public class MaintainController {
    /**
     * 服务对象
     */
    @Resource
    private MaintainService maintainService;


    @RequestMapping("index")
    private String index(){
        return "views/maintain/list.html";
    }


    @RequestMapping(value = "selectMaintainList", method = {RequestMethod.GET})
    @ResponseBody
    public ModelMap selectMaintainList(Authentication authentication,Maintain maintain, Integer page, Integer limit) {
        SysUser principal = (SysUser) authentication.getPrincipal();
        if (principal.getSysRole().getRoleType() == 2){
            maintain.setUserId(principal.getId());
        }
        PageInfo<Maintain> maintainList = maintainService.selectMaintainList(maintain,page,limit);
        return ReturnUtil.Success("查询成功", maintainList.getList(), maintainList.getTotal());
    }

    @PostMapping("insertMaintain")
    @ResponseBody
    public ModelMap insertMaintain(Maintain maintain, Authentication authentication) {
        SysUser user = (SysUser) authentication.getPrincipal();
        maintain.setUserId(user.getId());
        try {
            maintainService.insertMaintain(maintain);
            return ReturnUtil.Success("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnUtil.Error("操作失败");
        }
    }

    @GetMapping("deleteMaintainById")
    @ResponseBody
    public ModelMap deleteMaintainById(String id) {
        try {
            maintainService.deleteMaintainById(id);
            return ReturnUtil.Success("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnUtil.Error("删除失败");
        }
    }

    @PostMapping("updateMaintainById")
    @ResponseBody
    public ModelMap updateMaintainById(Maintain maintain) {

        try {
            maintainService.updateMaintainById(maintain);
            return ReturnUtil.Success("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnUtil.Error("操作失败");
        }
    }

    @RequestMapping("updateMaintainIndex")
    public String selectMaintainById(String id, Model model) {
        Maintain maintain = maintainService.selectMaintainById(id);
        model.addAttribute("maintain", maintain);
        return "views/maintain/maintainform";
    }

    @GetMapping("maintainSuccessById")
    @ResponseBody
    public ModelMap maintainSuccessById(String id) {
        try {
            maintainService.maintainSuccessById(id);
            return ReturnUtil.Success("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnUtil.Error("操作失败");
        }
    }
}


