package com.tieria.shopping.apis.user.controllers;

import com.tieria.shopping.apis.user.enums.UserLoginResult;
import com.tieria.shopping.apis.user.enums.UserSignUpResult;
import com.tieria.shopping.apis.user.services.UserService;
import com.tieria.shopping.apis.user.vos.UserLoginVo;
import com.tieria.shopping.apis.user.vos.UserSignUpVo;
import com.tieria.shopping.common.Constant;
import com.tieria.shopping.common.Converter;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

@RestController
@RequestMapping(value = "/apis/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //    -------------------------------------------------------------------------------------------- CREATE (sign up)
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signUpPost(HttpServletRequest request, HttpServletResponse response,
                             @RequestParam(name = "newName", defaultValue = "") String name,
                             @RequestParam(name = "newEmail", defaultValue = "") String email,
                             @RequestParam(name = "newPassword", defaultValue = "" ) String password,
                             @RequestParam(name = "newLevel", defaultValue = "") String strLevel) throws SQLException {
        int intLevel = Converter.stringToInt(strLevel, -1);
        UserSignUpVo userSignUpVo = new UserSignUpVo(name, email, password, intLevel);
        JSONObject jsonResponse = new JSONObject();
        if(intLevel > -1){
            UserSignUpResult userSignUpResult = this.userService.SignUp(userSignUpVo);
            request.getSession().setAttribute(Constant.Common.USER_SIGN_UP_RESULT, userSignUpResult);
            jsonResponse.put(Constant.Common.JSON_ENTRY_RESULT, userSignUpResult.name().toLowerCase());
        }
        return jsonResponse.toString(4);
    }

    //    -------------------------------------------------------------------------------------------- READ (Login)
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces =
            MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String loginPost(HttpServletRequest request, HttpServletResponse response,
                            @RequestParam(name = "name", defaultValue = "") String name,
                            @RequestParam(name = "password", defaultValue = "") String password) throws SQLException{
        UserLoginVo userLoginVo = new UserLoginVo(name, password, request);
        UserLoginResult userLoginResult = this.userService.login(userLoginVo);
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put(Constant.Common.JSON_ENTRY_RESULT, userLoginResult.name().toLowerCase());
        return jsonResponse.toString(4);
    }

    //    -------------------------------------------------------------------------------------------- DELETE (Logout)
    @RequestMapping(value = "/logout", method = RequestMethod.POST, produces =
            MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String logoutPost(HttpServletRequest request, HttpServletResponse response) throws SQLException{
        Converter.setUserVo(request, null);
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put(Constant.Common.JSON_ENTRY_RESULT, Constant.Common.LOGOUT);
        return jsonResponse.toString(4);
    }
}
