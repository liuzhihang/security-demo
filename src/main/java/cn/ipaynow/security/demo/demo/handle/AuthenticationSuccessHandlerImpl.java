package cn.ipaynow.security.demo.demo.handle;

import cn.ipaynow.security.demo.demo.bean.RespBean;
import cn.ipaynow.security.demo.demo.bean.UserDetailsImpl;
import com.alibaba.fastjson.JSON;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

/**
 * 用户登录成功之后的返回信息
 *
 * @author liuzhihang
 * @date 2019-06-04 14:20
 */
@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        RespBean respBean = new RespBean("0000", "用户登录成功");

        UserDetails userDetails = (UserDetailsImpl) authentication.getPrincipal();


        // TODO 自定义生成token

        response.setContentType("application/json;charset=UTF-8");

        response.getWriter().write(JSON.toJSONString(respBean));

    }
}