package cn.property.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    /**
     * 登陆页面
     *
     * @return
     */
    @RequestMapping("/user/login")
    public String login() {
        return "views/user/login";
    }

    @RequestMapping("index")
    public String index(Model model, Authentication authentication){
        return "1125zh3/default";
    }

    @RequestMapping("admin")
    public String admin(Model model, Authentication authentication){
        return "views/index";
    }
}
