package org.o7planning.sbsecurity.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.o7planning.sbsecurity.model.AppUser;
import org.springframework.jdbc.core.RowMapper;

public class AppUserMapper implements RowMapper<AppUser> {

    public static final String BASE_SQL //
            = "Select u.User_Id, u.User_Login, u.Encryted_Password, u.User_name, u.User_Birthday, u.User_Hometown, u.User_Company From App_User u ";

    @Override
    public AppUser mapRow(ResultSet rs, int rowNum) throws SQLException {

        Long userId = rs.getLong("User_Id");
        String userLogin = rs.getString("User_Login");
        String encrytedPassword = rs.getString("Encryted_Password");
        String userName = rs.getString("User_Name");
        Date userBDay = rs.getDate("User_Birthday");
        String userHometown = rs.getString("User_Hometown");
        String userCompany = rs.getString("User_Company");


        return new AppUser(userId, userLogin, encrytedPassword, userName, userBDay, userHometown, userCompany);
    }

}