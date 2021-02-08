package com.tieria.shopping.apis.customer.containers;

import com.tieria.shopping.apis.customer.enums.CustomerResult;
import com.tieria.shopping.apis.customer.vos.QnaVo;

import java.util.ArrayList;


public class CustomerResultContainer {
    private final CustomerResult customerResult;
    private final ArrayList<QnaVo> qnaList;

    public CustomerResultContainer(CustomerResult customerResult) {
        this.customerResult = customerResult;
        this.qnaList = null;
    }

    public CustomerResultContainer(CustomerResult customerResult, ArrayList<QnaVo> qnaList) {
        this.customerResult = customerResult;
        this.qnaList = qnaList;
    }

    public CustomerResult getCustomerResult() {
        return customerResult;
    }

    public ArrayList<QnaVo> getQnaList() {
        return qnaList;
    }
}
