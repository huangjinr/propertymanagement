package cn.property.security;

import cn.property.utils.AjaxResponseBody;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class EntryPointUnauthorizedHandler implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        PrintWriter out = httpServletResponse.getWriter();
        AjaxResponseBody respBody = new AjaxResponseBody();
        respBody.setMsg("认证失败，请联系管理员!");
        respBody.setCode(HttpServletResponse.SC_BAD_REQUEST);
        respBody.setData("");
        out.write(new ObjectMapper().writeValueAsString(respBody));
        out.flush();
        out.close();
    }
}
