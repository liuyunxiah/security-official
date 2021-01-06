package com.lyx.securityofficial.securityofficial.auth.mapper;

import com.lyx.securityofficial.securityofficial.auth.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    User selectUserbyUsername(String userName);
}