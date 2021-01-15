package com.tieria.shopping.common;

public class UserVo {
    private final int userIndex;
    private final String userName;
    private final String userEmail;
    private final String userPassword;
    private final int userLevel;

    public UserVo(int userIndex, String userName, String userEmail, String userPassword, int userLevel) {
        this.userIndex = userIndex;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userLevel = userLevel;
    }

    public int getUserIndex() {
        return userIndex;
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

    public int getUserLevel() {
        return userLevel;
    }
}
