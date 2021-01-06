package com.lyx.securityofficial.securityofficial.auth.service;

import com.lyx.securityofficial.securityofficial.auth.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //查询数据库()用户信息
        com.lyx.securityofficial.securityofficial.auth.domain.User user = userMapper.selectUserbyUsername(username);
        if(user == null){
            return null;
        }
        //查用户下的权限信息;
        String[] authorities = new String[0];//省略此处

        UserDetails userDetails = User.withUsername(user.getUserName()).password(user.getPassword())
                .authorities(authorities).build();
        return userDetails;
    }
}
