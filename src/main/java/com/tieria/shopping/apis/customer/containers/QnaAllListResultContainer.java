package com.tieria.shopping.apis.customer.containers;

import com.tieria.shopping.apis.customer.enums.CustomerResult;
import com.tieria.shopping.apis.customer.vos.QnaAllVo;

import java.util.ArrayList;

public class QnaAllListResultContainer {
    private final CustomerResult customerResult;
    private final ArrayList<QnaAllVo> qnaAllList;

    public QnaAllListResultContainer(CustomerResult customerResult) {
        this.customerResult = customerResult;
        this.qnaAllList = null;
    }

    public QnaAllListResultContainer(CustomerResult customerResult, ArrayList<QnaAllVo> qnaAllList) {
        this.customerResult = customerResult;
        this.qnaAllList = qnaAllList;
    }

    public CustomerResult getCustomerResult() {
        return customerResult;
    }

    public ArrayList<QnaAllVo> getQnaAllList() {
        return qnaAllList;
    }
}
