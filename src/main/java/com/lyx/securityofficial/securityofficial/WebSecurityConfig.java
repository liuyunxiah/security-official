package com.lyx.securityofficial.securityofficial;

import com.lyx.securityofficial.securityofficial.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author liuyunxia
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthService authService;

    /**
     * 安全拦截机制
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //具体的规则放到上面,宽泛的放到下面
        //web授权 : 尽量使用基于资源url的方式
        //方法授权

        http.authorizeRequests()
            .antMatchers("/index").hasAuthority("p1")
            .antMatchers("/home").hasAuthority("p2")
            .antMatchers("/login")
            .permitAll() //无保护的url路径
            .anyRequest().authenticated() //其他的必须认证通过
            .and()
            .formLogin()//允许表单登陆
            .loginPage("/login")//登录页面
            .loginProcessingUrl("/form/login")
            .failureForwardUrl("/error") // 错误页面
            .defaultSuccessUrl("/index") //登录成功后跳转的页面
            .and()
            .logout()
            .permitAll()
            .and()
            .cors()//开启跨域
            .and().csrf().disable();
    }

//    /**
//     * 基于内存: 查询用户
//     * @param auth
//     * @throws Exception
//     */
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().passwordEncoder(passwordEncoder())
//                .withUser("daming").password("123").roles("USER").authorities("p1");
//        auth.inMemoryAuthentication().passwordEncoder(passwordEncoder())
//                .withUser("erming").password("123").roles("USER").authorities("p2");
//    }

    //查数据库
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authService).passwordEncoder(passwordEncoder());
    }

    /**
     * 密碼校驗方式
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
