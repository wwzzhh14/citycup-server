package com.springmvc.config;

/**
 * Created by wzh on 16/9/11.
 */
public class UserInfo {
    String userName;
    boolean isLogin;

    public UserInfo(String userName, boolean isLogin) {
        this.userName = userName;
        this.isLogin = isLogin;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }
}
