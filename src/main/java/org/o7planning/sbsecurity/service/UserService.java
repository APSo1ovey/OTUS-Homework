package org.o7planning.sbsecurity.service;

import org.springframework.stereotype.Service;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.o7planning.sbsecurity.model.AppUser;
import org.o7planning.sbsecurity.mapper.AppUserMapper;


@Service
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final SqlSessionTemplate sqlSessionTemplate;

    public UserService(BCryptPasswordEncoder bCryptPasswordEncoder, SqlSessionTemplate sqlSessionTemplate) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    public AppUser findUserByUserName(String username) throws Exception {
        AppUser userLogin = null;
        try (SqlSession sqlSession = sqlSessionTemplate.getSqlSessionFactory().openSession()) {
            AppUserMapper AppUserMapper = sqlSession.getMapper(AppUserMapper.class);
            userLogin = AppUserMapper.mapRow(userLogin);

        }
        return userLogin;
    }
/*
    public int saveUser(AppUser user) throws Exception{
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        int result = 0;
        try (SqlSession sqlSession = sqlSessionTemplate.getSqlSessionFactory().openSession()) {
            AppUserMapper userMapper = sqlSession.getMapper(AppUserMapper.class);
            result = userMapper.saveUser(user);

        }
        return result;
    }

 */
}