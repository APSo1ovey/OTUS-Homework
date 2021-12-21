package org.o7planning.sbsecurity.service;

import org.o7planning.sbsecurity.dao.AppUserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.o7planning.sbsecurity.model.AppUser;
import org.o7planning.sbsecurity.mapper.UserMapper;


@Service
    public class UserService {
        private static final Logger logger = LoggerFactory.getLogger(UserService.class);
        private final BCryptPasswordEncoder bCryptPasswordEncoder;
        private final AppUserDAO appUserDAO;
        public UserService(BCryptPasswordEncoder bCryptPasswordEncoder, AppUserDAO appUserDAO) {
            this.bCryptPasswordEncoder = bCryptPasswordEncoder;
            this.appUserDAO = appUserDAO;
        }

        public AppUser findUserByUserName(String username) throws Exception {
            return appUserDAO.findUserAccount(username);
        }

        public int saveUser(AppUser user) throws Exception{
            user.setEncrytedPassword(bCryptPasswordEncoder.encode(user.getEncrytedPassword()));
            return appUserDAO.saveUser(user);
        }
}