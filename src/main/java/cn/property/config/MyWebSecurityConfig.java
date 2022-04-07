package cn.property.config;

import cn.property.security.MyAccessDeniedHandler;
import cn.property.security.MyAuthenticationFailureHandler;
import cn.property.security.MyAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;

@Configuration
public class MyWebSecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

    @Autowired
    private MyAccessDeniedHandler myAccessDeniedHandler;

    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;


    //默认Url根路径跳转到/login，此url为spring security提供
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("redirect:/user/login");
    }

    /**
     * 自行注入一个PasswordEncoder。
     * @return
     */
    @Bean
    public PasswordEncoder getPassWordEncoder(){
        return new BCryptPasswordEncoder();
        //return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //表单登录
        http.csrf().disable()
                .headers().frameOptions().disable().and()
                .formLogin()
                .loginPage("/user/login")
                .loginProcessingUrl("/login")
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(myAuthenticationFailureHandler)
                .and()
                .authorizeRequests()
                .antMatchers("/","/user/login","/layuiadmin/**","/login","/views/user/reg.html","/register/insertUser").permitAll()
                .anyRequest().authenticated()
                //.anyRequest().access("@myAccessImpl.hasPermission(request,authentication)")
                .and()
                .exceptionHandling()
                //.authenticationEntryPoint(entryPointUnauthorizedHandler)
                .accessDeniedHandler(myAccessDeniedHandler);
    }
}
