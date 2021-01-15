package com.tieria.shopping.apis.user.vos;

import com.tieria.shopping.common.Sha512;

public class UserSignUpVo {
    private String userName;
    private String userEmail;
    private String userPassword;
    private String hashPassword;
    private int userLevel;

    public UserSignUpVo(String userName, String userEmail, String userPassword, int userLevel) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.hashPassword = Sha512.hash(this.userPassword);
        this.userLevel = userLevel;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public int getUserLevel() {
        return userLevel;
    }
}
