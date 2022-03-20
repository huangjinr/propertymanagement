package cn.property.security;

import cn.property.utils.AjaxResponseBody;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        AjaxResponseBody respBody = new AjaxResponseBody();
        String msg = e.getMessage() != null  ? e.getMessage() : "";
        if("Bad credentials".equals(msg)){
            msg ="用户名或密码错误";
        }
        respBody.setMsg(msg);
        respBody.setCode(-1);
        ObjectMapper om = new ObjectMapper();
        PrintWriter out = httpServletResponse.getWriter();
        out.write(om.writeValueAsString(respBody));
        out.flush();
        out.close();
    }
}
