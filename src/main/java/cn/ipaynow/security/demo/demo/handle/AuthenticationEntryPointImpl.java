package cn.ipaynow.security.demo.demo.handle;

import cn.ipaynow.security.demo.demo.bean.RespBean;
import com.alibaba.fastjson.JSON;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户登录认证, 未登录返回信息
 *
 * @author liuzhihang
 * @date 2019-06-04 13:52
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {


        response.setContentType("application/json;charset=UTF-8");

        response.getWriter().write(JSON.toJSONString(new RespBean("0001", "用户未登录")));


    }
}
