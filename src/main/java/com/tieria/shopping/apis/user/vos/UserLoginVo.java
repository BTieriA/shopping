package com.tieria.shopping.apis.user.vos;

import com.tieria.shopping.common.Sha512;

import javax.servlet.http.HttpServletRequest;

public class UserLoginVo {
    private final String userName;
    private final String password;
    private final String hashedPassword;
    private final HttpServletRequest request;

    public UserLoginVo(String userName, String password, HttpServletRequest request) {
        this.userName = userName;
        this.password = password;
        this.hashedPassword = Sha512.hash(this.password);
        this.request = request;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public HttpServletRequest getRequest() {
        return request;
    }
}
