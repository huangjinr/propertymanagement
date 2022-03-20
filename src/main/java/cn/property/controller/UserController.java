package cn.property.controller;

import cn.property.model.SysUser;
import cn.property.service.UserService;
import cn.property.utils.ReturnUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping("index")
    private String index(){
        return "views/user/user/list.html";
    }
    /**
     * 查询用户列表
     *
     * @return
     */
    @GetMapping("selectUserList")
    @ResponseBody
    public ModelMap selectUserList(SysUser sysUser, Integer page, Integer limit) {
        PageInfo<SysUser> userList = userService.selectUserList(sysUser,page,limit);
        return ReturnUtil.Success("查询成功", userList.getList(), userList.getTotal());
    }

    /**
     * 添加用户
     *
     * @param sysUser
     * @return
     */
    @PostMapping("insertUser")
    @ResponseBody
    public ModelMap insertUser(SysUser sysUser) {
        SysUser user = userService.selectUserByUsername(sysUser.getUsername());
        if (user != null){
            return ReturnUtil.Error("用户名已存在");
        }
        try {
            userService.insertUser(sysUser);
            return ReturnUtil.Success("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnUtil.Error("操作失败");
        }
    }

    /**
     * 返回编辑用户界面
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("updateUserIndex")
    public String selectUserById(String id, Model model) {
        SysUser sysUser = userService.selectUserById(id);
        model.addAttribute("sysUser", sysUser);
        return "views/user/user/userform";
    }

    /*
     * 更新用户信息
     *
     * @param sysUser
     * @return
     */
    @RequestMapping("updateUser")
    @ResponseBody
    public ModelMap updateUser(SysUser sysUser) {
        try {
            userService.updateUser(sysUser);
            return ReturnUtil.Success("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnUtil.Error("操作失败");
        }
    }

    /**
     * 根据id删除用户
     *
     * @param id
     * @return
     */
    @GetMapping("deleteUserById")
    @ResponseBody
    public ModelMap deleteUserById(String id) {
        try {
            userService.deleteUserById(id);
            return ReturnUtil.Success("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnUtil.Error("删除失败");
        }
    }


    /**
     * 根据id批量删除用户
     *
     * @param ids
     * @return
     */
    @GetMapping("deleteUserByIds")
    @ResponseBody
    public ModelMap deleteUserByIds(@RequestParam(value = "ids") List<String> ids) {
        try {
            userService.deleteByIds(ids);
            return ReturnUtil.Success("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            //手工回滚异常
            return ReturnUtil.Error("删除失败");
        }
    }
}
