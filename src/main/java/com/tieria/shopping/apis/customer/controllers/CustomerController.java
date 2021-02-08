package com.tieria.shopping.apis.customer.controllers;

import com.tieria.shopping.apis.customer.containers.CustomerResultContainer;
import com.tieria.shopping.apis.customer.enums.CustomerResult;
import com.tieria.shopping.apis.customer.services.CustomerService;
import com.tieria.shopping.apis.customer.vos.InsertQnaVo;
import com.tieria.shopping.apis.customer.vos.QnaVo;
import com.tieria.shopping.common.Constant;
import com.tieria.shopping.common.Converter;
import com.tieria.shopping.common.UserVo;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

@RestController
@RequestMapping(
        value = "/apis/customer",
        method = {RequestMethod.GET, RequestMethod.POST},
        produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    //    -------------------------------------------------------------------------------------------- CREATE (insert)
    // insert QNA
    @RequestMapping(value = "/addQna")
    public String addQna(HttpServletRequest request, HttpServletResponse response,
                         @RequestParam(value = "title", defaultValue = "") String title,
                         @RequestParam(value = "content", defaultValue = "") String content) throws SQLException {
        UserVo userVo = Converter.getUserVo(request);
        InsertQnaVo insertQnaVo = new InsertQnaVo(title, content, userVo.getUserIndex());
        CustomerResult customerResult = this.customerService.insertQna(userVo, insertQnaVo);
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put(Constant.Common.JSON_ENTRY_RESULT, customerResult.name().toLowerCase());
        return jsonResponse.toString(4);
    }

    //    -------------------------------------------------------------------------------------------- READ (select)
   @RequestMapping(value = "/qnaList")
    public String getQnaList(HttpServletRequest request, HttpServletResponse response,
                             @RequestParam(value = "page", defaultValue = "1") String strPage) throws SQLException {
        UserVo userVo = Converter.getUserVo(request);
        int page = Converter.stringToInt(strPage, 1);
        int totalCount = this.customerService.getTotalQnaCount();
        int maxPage = totalCount % 10 == 0 ? totalCount / 10 : (int) (Math.floor((double)(totalCount / 10)) + 1);
        int startPage = (page > 5) ? (page - 4) : 1;
        int endPage = (maxPage > 10 ) ? (page + 4) : maxPage;
        CustomerResultContainer customerResultContainer = this.customerService.getQnaList(userVo, page);
        JSONObject jsonResponse = new JSONObject();
        JSONArray jsonQnaList = new JSONArray();

        jsonResponse.put("page", page);
        jsonResponse.put("max_page", maxPage);
        jsonResponse.put("start_page", startPage);
        jsonResponse.put("end_page", endPage);

        if (customerResultContainer.getCustomerResult() == CustomerResult.SUCCESS) {
            for (QnaVo qnaVo : customerResultContainer.getQnaList()) {
                JSONObject jsonQna = new JSONObject();
                jsonQna.put("qnaIndex", qnaVo.getCustomerIndex());
                jsonQna.put("qnaTitle", qnaVo.getCustomerTitle());
                jsonQna.put("qnaContent", qnaVo.getCustomerContent());
                jsonQna.put("qnaDate", qnaVo.getCustomerDate());
                jsonQna.put("userName", qnaVo.getCustomerName());
                jsonQnaList.put(jsonQna);
            }

            jsonResponse.put("qnaList", jsonQnaList);
        } else if (customerResultContainer.getCustomerResult() == CustomerResult.NOT_ALLOWED){
            jsonResponse.put("qnaList", "no_authorized");
        } else {
            jsonResponse.put("qnaList", "no_data");
        }
        return jsonResponse.toString(4);
    }
}
