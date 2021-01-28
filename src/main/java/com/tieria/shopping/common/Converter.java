package com.tieria.shopping.common;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


public class Converter {
    private Converter() {
    }

    public static void setUserVo(HttpServletRequest request, UserVo userVo) {
        request.getSession().setAttribute("UserVo", userVo);
    }

    public static UserVo getUserVo(HttpServletRequest request) {
        Object userVoObject = request.getSession().getAttribute("UserVo");
        UserVo userVo = null;
        if(userVoObject instanceof UserVo) {
            userVo = (UserVo) userVoObject;
        }
        return userVo;
    }

    public static void setDetailVo(HttpServletRequest request, DetailVo detailVo) {
        request.getSession().setAttribute("DetailVo", detailVo);
    }

    public static DetailVo getDetailVo(HttpServletRequest request) {
        Object detailVoObject = request.getSession().getAttribute("DetailVo");
        DetailVo detailVo = null;
        if(detailVoObject instanceof DetailVo){
            detailVo = (DetailVo) detailVoObject;
        }
        return detailVo;
    }

    public static int stringToInt(String idText, int fallback) {
        try {
            return Integer.parseInt(idText);
        } catch (Exception ignored) {
            return fallback;
        }
    }

    public static String imageToString(MultipartFile image) throws
            IOException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("data:image/png;base64,");
        stringBuilder.append(StringUtils.newStringUtf8(Base64.encodeBase64(image.getBytes())));
        return stringBuilder.toString();
    }
}
