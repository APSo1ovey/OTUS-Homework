package org.o7planning.sbsecurity.dao;

import javax.sql.DataSource;

import org.o7planning.sbsecurity.mapper.AppUserMapper;
import org.o7planning.sbsecurity.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class AppUserDAO extends JdbcDaoSupport {

    @Autowired
    public AppUserDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public AppUser findUserAccount(String userLogin) {
        // Select .. from App_User u Where u.User_Login = ?
        String sql = AppUserMapper.BASE_SQL + " where u.User_Login = ? ";

        Object[] params = new Object[] { userLogin };
        AppUserMapper mapper = new AppUserMapper();
        try {
            AppUser userInfo = this.getJdbcTemplate().queryForObject(sql, params, mapper);
            return userInfo;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public int saveUser(AppUser user) throws Exception{
        String sql = "insert into table (userLogin, encrytedPassword, userName, userBDay, userHometown, userCompany) VALUES(?,?,?,?,?,?)";
        return this.getJdbcTemplate().update(sql,user.getUserLogin(), user.getEncrytedPassword(), user.getUserName(), user.getUserBDay(), user.getUserHometown(), user.getUserCompany());
    }

}