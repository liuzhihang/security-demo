package cn.ipaynow.security.demo.demo.service;

import cn.ipaynow.security.demo.demo.bean.DbUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuzhihang
 */
@Component("userDetailServiceImpl")
public class UserDetailServiceImpl implements UserDetailsService {


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("本次验证用户为" + username);

        if (StringUtils.isEmpty(username)) {
            throw new UsernameNotFoundException("UserDetailsService没有接收到用户账号");
        } else {
            /**
             * 根据用户名查找用户信息, 新创建user 模拟是从数据库中查询到的
             */

            DbUser dbUser = new DbUser();
            dbUser.setUserName("liuzhihang");
            // 123456
            dbUser.setPassword("$2a$10$vXxlwiz9dL6dNMpxu/uulOv4uE2qbJC1NF5bz1hfpW7amJwpP.LBS");
            dbUser.setRole("USER");


            // if (dbUser == null) {
            //     throw new UsernameNotFoundException(String.format("用户'%s'不存在", username));
            // }
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            //封装用户信息和角色信息到SecurityContextHolder全局缓存中
            grantedAuthorities.add(new SimpleGrantedAuthority(dbUser.getRole()));
            /**
             * 创建一个用于认证的用户对象并返回，包括：用户名，密码，角色
             */
            return new User(dbUser.getUserName(), dbUser.getPassword(), grantedAuthorities);
        }
    }
}
