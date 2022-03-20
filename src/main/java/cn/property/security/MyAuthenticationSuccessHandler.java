package cn.property.security;



import cn.property.utils.AjaxResponseBody;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created with IDEA
 * author:hxb
 *
 * @Date: 2019/5/22 09:47
 * @Description: 登陆成功
 */
@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        AjaxResponseBody respBody = new AjaxResponseBody();
        respBody.setMsg("登录成功");
        respBody.setCode(0);
        respBody.setData(JSONObject.toJSONString("loginSuccess"));
        ObjectMapper om = new ObjectMapper();
        PrintWriter out = response.getWriter();
        out.write(om.writeValueAsString(respBody));
        out.flush();
        out.close();
    }
}
