package cn.property.controller;

import cn.property.model.SysPermission;
import cn.property.service.PermissionService;
import cn.property.utils.ReturnUtil;
import cn.property.utils.SysPermissionTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    /**
     * 权限管理列表页面
     *
     * @return
     */
    @RequestMapping(value = "index", method = {RequestMethod.GET})
    public String index() {
        return "views/permission/list";
    }

    /**
     * 查询所有的权限
     *
     * @return
     */
    @RequestMapping(value = "selectPermissionList", method = {RequestMethod.GET})
    @ResponseBody
    public ModelMap selectPermissionList() {
        //所有的目录
        List<SysPermission> sysResourcesAllList = permissionService.selectPermissionList();
        return ReturnUtil.Success("加载成功", sysResourcesAllList);
    }

    /**
     * 查询所有的资源tree
     *
     * @return
     */
    @RequestMapping(value = "findAll", method = {RequestMethod.GET})
    @ResponseBody
    public ModelMap findAll() {
        //所有的目录
        List<SysPermission> sysPermissionList = permissionService.selectPermissionList();
        SysPermissionTree sysPermissionTree = new SysPermissionTree(sysPermissionList);
        List<Map<String, Object>> mapList = sysPermissionTree.buildTree();
        return ReturnUtil.Success("加载成功", mapList);
    }

    /**
     * 添加权限
     *
     * @param sysPermission
     * @return
     */
    @PostMapping("insertPermission")
    @ResponseBody
    public ModelMap insertPermission(SysPermission sysPermission) {
        try {
            permissionService.insertPermission(sysPermission);
            return ReturnUtil.Success("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnUtil.Error("操作失败");
        }
    }

    /**
     * 返回编辑权限界面
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("updatePermissionIndex")
    public String selectPermissionById(String id, Model model) {
        SysPermission sysPermission = permissionService.selectPermissionById(id);
        String permissionParentName = permissionService.selectParentNameByParentName(id);
        model.addAttribute("sysPermission", sysPermission);
        model.addAttribute("permissionParentName", permissionParentName);
        return "views/permission/permissionform";
    }

    /**
     * 编辑权限
     *
     * @param sysPermission
     * @return
     */
    @PostMapping("updatePermission")
    @ResponseBody
    public ModelMap updatePermission(SysPermission sysPermission) {
        try {
            permissionService.updatePermission(sysPermission);
            return ReturnUtil.Success("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnUtil.Error("操作失败");
        }
    }

    /**
     * 根据id删除权限
     *
     * @param id
     * @return
     */
    @GetMapping("deletePermissionById")
    @ResponseBody
    public ModelMap deletePermissionById(String id) {
        try {
            permissionService.deletePermissionById(id);
            return ReturnUtil.Success("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnUtil.Error("删除失败");
        }
    }
}
