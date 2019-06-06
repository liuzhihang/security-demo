package cn.ipaynow.security.demo.demo.service;

import cn.ipaynow.security.demo.demo.filter.JwtPerTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.annotation.Resource;

/**
 * @author liuzhihang
 * @date 2019-06-03 14:25
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailServiceImpl userDetailServiceImpl;

    @Resource(name = "logoutSuccessHandlerImpl")
    private LogoutSuccessHandler logoutSuccessHandler;

    @Resource(name = "authenticationEntryPointImpl")
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Resource(name = "authenticationSuccessHandlerImpl")
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Resource(name = "authenticationFailureHandlerImpl")
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Resource(name = "accessDeniedHandlerImpl")
    private AccessDeniedHandler accessDeniedHandler;

    @Resource
    private JwtPerTokenFilter jwtPerTokenFilter;

    /**
     * 配置用户信息
     *
     * @param auth
     * @throws Exception
     */
    @Autowired
    public void configureUserInfo(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailServiceImpl);

    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /**
         * 表单登录：使用默认的表单登录页面和登录端点/login进行登录
         * 退出登录：使用默认的退出登录端点/logout退出登录
         * 记住我：使用默认的“记住我”功能，把记住用户已登录的Token保存在内存里，记住30分钟
         * 权限：除了/toHome和/toUser之外的其它请求都要求用户已登录
         * 注意：Controller中也对URL配置了权限，如果WebSecurityConfig中和Controller中都对某文化URL配置了权限，则取较小的权限
         */
        http
                // 使用JWT, 关闭session
                .csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and().httpBasic().authenticationEntryPoint(authenticationEntryPoint)

                // 登录的权限, 成功返回信息, 失败返回信息
                .and()
                .formLogin().permitAll()
                .loginProcessingUrl("/login")
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)

                // 登出的权限以及成功后的处理器
                .and()
                .logout().permitAll().logoutSuccessHandler(logoutSuccessHandler)

                // 配置url 权限 antMatchers: 匹配url 权限
                .and()
                .authorizeRequests()
                .antMatchers("/login", "/toHome", "/toUser").permitAll()
                .antMatchers("/toAdmin").hasRole("ADMIN")
                .antMatchers("/toEmployee").access("hasRole('ADMIN') or hasRole('EMPLOYEE')")
                // 其他需要登录才能访问
                .anyRequest().authenticated()

                // 访问无权限 location 时
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler)
                // 记住我
                .and()
                .rememberMe().rememberMeParameter("remember-me").userDetailsService(userDetailServiceImpl).tokenValiditySeconds(300)

                // 自定义过滤
                .and()
                .addFilterBefore(jwtPerTokenFilter, UsernamePasswordAuthenticationFilter.class).headers().cacheControl();
        // .addFilter(new JwtLoginFilter(authenticationManager())).addFilter(new JwtAuthenticationFilter(authenticationManager()));
    }

    /**
     * 密码加密器
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        /**
         * BCryptPasswordEncoder：相同的密码明文每次生成的密文都不同，安全性更高
         */
        return new BCryptPasswordEncoder();
    }


}
