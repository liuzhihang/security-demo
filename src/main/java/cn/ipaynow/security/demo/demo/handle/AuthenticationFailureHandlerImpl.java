package cn.ipaynow.security.demo.demo.handle;

import cn.ipaynow.security.demo.demo.bean.RespBean;
import com.alibaba.fastjson.JSON;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户登录认证失败返回的信息
 *
 * @author liuzhihang
 * @date 2019-06-04 13:57
 */
@Component
public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        response.setContentType("application/json;charset=UTF-8");

        response.getWriter().write(JSON.toJSONString(new RespBean("0002", "登录失败")));


    }
}
