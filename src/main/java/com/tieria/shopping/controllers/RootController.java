package com.tieria.shopping.controllers;

import com.tieria.shopping.common.Converter;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/", method = RequestMethod.GET)
public class RootController {
    @RequestMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
    public String LogGet(HttpServletRequest request, HttpServletResponse response){
        return "login";
    }

    @RequestMapping(value = "main", produces = MediaType.TEXT_HTML_VALUE)
    public String IndexGet(HttpServletRequest request, HttpServletResponse response) {
        if (Converter.getUserVo(request) == null) {
            return "login";
        } else {
            return "main";
        }
    }

    @RequestMapping(value = "admin", produces = MediaType.TEXT_HTML_VALUE)
    public String AddGet(HttpServletRequest request, HttpServletResponse response) {
        if (Converter.getUserVo(request).getUserLevel() == 1) {
            return "admin/admin";
        } else if (Converter.getUserVo(request) == null) {
            return "login";
        } else {
            return "main";
        }
    }
}
