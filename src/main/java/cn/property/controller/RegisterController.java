package cn.property.controller;


import cn.property.model.SysUser;
import cn.property.service.UserService;
import cn.property.utils.ReturnUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("register")
public class RegisterController {

    @Autowired
    private UserService userService;

    @RequestMapping("insertUser")
    @ResponseBody
    public ModelMap regist(SysUser user){
        try {
            userService.registUser(user);
            return ReturnUtil.Success("注册成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnUtil.Error("添加失败");
        }
    }
}
