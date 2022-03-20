package cn.property.controller;

import cn.property.model.SysPermission;
import cn.property.model.SysRole;
import cn.property.model.SysRolePermission;
import cn.property.model.SysUser;
import cn.property.service.RoleService;
import cn.property.utils.ReturnUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 角色管理列表页面
     *
     * @return
     */
    @RequestMapping(value = "index", method = {RequestMethod.GET})
    public String index() {
        return "views/role/list";
    }

    /**
     * 查询所有的角色
     *
     * @return
     */
    @RequestMapping(value = "selectRoleList", method = {RequestMethod.GET})
    @ResponseBody
    public ModelMap selectRoleList(SysRole sysRole,Integer page, Integer limit) {
        PageInfo<SysRole> selectRoleList = roleService.selectRoleList(sysRole,page,limit);
        return ReturnUtil.Success("加载成功", selectRoleList.getList(),selectRoleList.getTotal());
    }

    /**
     * 添加角色
     *
     * @param sysRole
     * @return
     */
    @PostMapping("insertRole")
    @ResponseBody
    public ModelMap insertRole(SysRole sysRole) {
        try {
            roleService.insertRole(sysRole);
            return ReturnUtil.Success("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnUtil.Error("操作失败");
        }
    }

    /**
     * 根据id删除角色
     *
     * @param id
     * @return
     */
    @GetMapping("deleteRoleById")
    @ResponseBody
    public ModelMap deleteRoleById(String id) {
        try {
            roleService.deleteRoleById(id);
            return ReturnUtil.Success("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnUtil.Error("删除失败");
        }
    }

    /**
     * 角色管理详情页面
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/updateRoleIndex", method = {RequestMethod.GET})
    public String updateRoleIndex(String id, Model model) {

        SysRole sysRole = roleService.selectRoleAndPermissionById(id);
        if (sysRole != null && !CollectionUtils.isEmpty(sysRole.getSysPermissionList())) {
            List<SysPermission> sysPermissionList = sysRole.getSysPermissionList();
            String sysResourcesIds = "";
            for (SysPermission sysPermission : sysPermissionList) {
                sysResourcesIds += sysPermission.getId() + ",";
            }
            model.addAttribute("sysPermissionIds", sysResourcesIds);
        }
        model.addAttribute("sysRole", sysRole);
        return "views/role/roleform";
    }

    /**
     * 编辑角色
     *
     * @param sysRole
     * @return
     */
    @PostMapping("updateRoleById")
    @ResponseBody
    public ModelMap updateRoleById(SysRole sysRole) {
        try {
            roleService.updateRoleById(sysRole);
            return ReturnUtil.Success("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnUtil.Error("操作失败");
        }
    }
}
