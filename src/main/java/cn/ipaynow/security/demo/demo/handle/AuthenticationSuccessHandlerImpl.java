package cn.ipaynow.security.demo.demo.handle;

import cn.ipaynow.security.demo.demo.bean.RespBean;
import cn.ipaynow.security.demo.demo.util.JwtTokenUtil;
import com.alibaba.fastjson.JSON;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户登录成功之后的返回信息
 *
 * @author liuzhihang
 * @date 2019-06-04 14:20
 */
@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        RespBean respBean = new RespBean("0000", "用户登录成功");

        UserDetails userDetails = (User) authentication.getPrincipal();


        // TODO 自定义生成token
        String jwtToken = jwtTokenUtil.generateToken(userDetails);

        respBean.setJwtToken(jwtToken);



        response.setContentType("application/json;charset=UTF-8");

        response.getWriter().write(JSON.toJSONString(respBean));

    }
}