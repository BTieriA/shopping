package com.tieria.shopping.apis.user.services;

import com.tieria.shopping.apis.user.daos.UserDao;
import com.tieria.shopping.apis.user.enums.UserLoginResult;
import com.tieria.shopping.apis.user.enums.UserSignUpResult;
import com.tieria.shopping.apis.user.vos.UserLoginVo;
import com.tieria.shopping.apis.user.vos.UserSignUpVo;
import com.tieria.shopping.common.Constant;
import com.tieria.shopping.common.Converter;
import com.tieria.shopping.common.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Service
public class UserService {
    private static final String PASSWORD_REGEX = "^([0-9a-zA-Z`~!@#$%^&*()\\-_=+\\[{\\]}\\\\|;:'\",<.>/?]{4,100})$";

    private final DataSource dataSource;
    private final UserDao userDao;

    @Autowired
    public UserService(DataSource dataSource, UserDao userDao) {
        this.dataSource = dataSource;
        this.userDao = userDao;
    }

    //    -------------------------------------------------------------------------------------------- CREATE (Sign up)
    public UserSignUpResult SignUp(UserSignUpVo userSignUpVo) throws SQLException {
        UserSignUpResult userSignUpResult;
        try(Connection connection = this.dataSource.getConnection()){
            if (this.userDao.countName(connection, userSignUpVo.getUserName()) > 0) {
                userSignUpResult = UserSignUpResult.NAME_DUPLICATION;
            } else if (this.userDao.countEmail(connection, userSignUpVo.getUserName()) > 0) {
                userSignUpResult = UserSignUpResult.EMAIL_DUPLICATION;
            } else if((userSignUpVo.getUserLevel() == 1) && (!userSignUpVo.getUserPassword().equals(Constant.Common.ADMIN_PASSWORD))){
                userSignUpResult = UserSignUpResult.UNAUTHORIZED;
            } else {
                this.userDao.insertUser(connection, userSignUpVo);
                if (this.userDao.countName(connection, userSignUpVo.getUserName()) > 0) {
                    // Name 1명이라도 있다면 등록 되었기 때문에 SUCCESS
                    userSignUpResult = UserSignUpResult.SUCCESS;
                } else {
                    userSignUpResult = UserSignUpResult.FAILURE;
                }
            }
        }
        return userSignUpResult;
    }

    //    -------------------------------------------------------------------------------------------- READ (Login)
    public UserLoginResult login(UserLoginVo userLoginVo) throws SQLException{
        UserLoginResult userLoginResult;
        try (Connection connection = this.dataSource.getConnection()) {
            UserVo userVo = this.userDao.selectUser(connection, userLoginVo);
            if(userVo == null) {
                userLoginResult = UserLoginResult.FAILURE;
            } else {
                Converter.setUserVo(userLoginVo.getRequest(), userVo);
                userLoginResult = UserLoginResult.SUCCESS;
            }
        }
        return userLoginResult;
    }

}
