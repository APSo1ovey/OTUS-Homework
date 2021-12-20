package org.o7planning.sbsecurity.model;

import java.sql.Date;

public class AppUser {

    private Long userId;
    private String userLogin;
    private String encrytedPassword;
    private String userName;
    private Date userBDay;
    private String userHometown;
    private String userCompany;


    public AppUser() {
    }

    public AppUser(Long userId, String userLogin, String encrytedPassword, String userName, Date userBDay, String userHometown, String userCompany) {
        this.userId = userId;
        this.userLogin = userLogin;
        this.encrytedPassword = encrytedPassword;
        this.userName = userName;
        this.userBDay = userBDay;
        this.userHometown = userHometown;
        this.userCompany = userCompany;
    }

    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserLogin() {
        return userLogin;
    }
    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getEncrytedPassword() {
        return encrytedPassword;
    }
    public void setEncrytedPassword(String encrytedPassword) {
        this.encrytedPassword = encrytedPassword;
    }
    @Override
    public String toString() {
        return this.userLogin + "/" + this.encrytedPassword;
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getUserBDay() {
        return userBDay;
    }
    public void setUserName(Date userBDay) {
        this.userBDay = userBDay;
    }

    public String getUserHometown() {
        return userHometown;
    }
    public void setUserHometown(String userHometown) {
        this.userHometown = userHometown;
    }

    public String getUserCompany() {
        return userCompany;
    }
    public void setUserCompany(String userCompany) {
        this.userCompany = userCompany;
    }

}