package com.tieria.shopping.apis.customer.containers;

import com.tieria.shopping.apis.customer.enums.CustomerResult;
import com.tieria.shopping.apis.customer.vos.QnaVo;

public class QuestionResultContainer {
    private final CustomerResult customerResult;
    private final QnaVo qnaVo;

    public QuestionResultContainer(CustomerResult customerResult) {
        this.customerResult = customerResult;
        this.qnaVo = null;
    }

    public QuestionResultContainer(CustomerResult customerResult, QnaVo qnaVo) {
        this.customerResult = customerResult;
        this.qnaVo = qnaVo;
    }

    public CustomerResult getCustomerResult() {
        return customerResult;
    }

    public QnaVo getQnaVo() {
        return qnaVo;
    }
}
