package cn.ipaynow.security.demo.demo.handle;

import cn.ipaynow.security.demo.demo.bean.RespBean;
import com.alibaba.fastjson.JSON;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 当用户访问无权限页面时, 返回信息
 *
 * @author liuzhihang
 * @date 2019-06-04 14:03
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

        response.setContentType("application/json;charset=UTF-8");

        response.getWriter().write(JSON.toJSONString(new RespBean("0003", "用户无权限访问 ")));

    }
}
