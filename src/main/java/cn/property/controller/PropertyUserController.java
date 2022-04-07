package cn.property.controller;

import cn.property.model.SysUser;
import cn.property.service.UserService;
import cn.property.utils.ReturnUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("propertyUser")
public class PropertyUserController {

    @RequestMapping("index")
    private String index(){
        return "views/user/propertyuser/list.html";
    }

    @Autowired
    private UserService userService;

    /**
     * 查询物业管理员列表
     *
     * @return
     */
    @GetMapping("selectPropertyUserList")
    @ResponseBody
    public ModelMap selectPropertyUserList(SysUser sysUser, Integer page, Integer limit) {
        PageInfo<SysUser> userList = userService.selectPropertyUserList(sysUser,page,limit);
        return ReturnUtil.Success("查询成功", userList.getList(), userList.getTotal());
    }
}
