package org.o7planning.sbsecurity.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.o7planning.sbsecurity.model.AppUser;
import org.o7planning.sbsecurity.dao.AppUserDAO;

import java.util.List;

@Mapper
public interface UserMapper {
    public int saveUser(AppUser user) throws Exception;
}
