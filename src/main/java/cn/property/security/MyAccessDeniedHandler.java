package cn.property.security;

import cn.property.utils.AjaxResponseBody;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
        PrintWriter out = httpServletResponse.getWriter();
        AjaxResponseBody respBody = new AjaxResponseBody();
        respBody.setMsg("没有访问权限!");
        respBody.setCode(HttpServletResponse.SC_FORBIDDEN);
        out.write(new ObjectMapper().writeValueAsString(respBody));
        out.flush();
        out.close();
    }
}
