package com.tieria.shopping.apis.customer.containers;

import com.tieria.shopping.apis.customer.enums.CustomerResult;
import com.tieria.shopping.apis.customer.vos.AnswerVo;

public class AnsResultContainer {
    private final CustomerResult customerResult;
    private final AnswerVo answerVo;

    public AnsResultContainer(CustomerResult customerResult) {
        this.customerResult = customerResult;
        this.answerVo = null;
    }

    public AnsResultContainer(CustomerResult customerResult, AnswerVo answerVo) {
        this.customerResult = customerResult;
        this.answerVo = answerVo;
    }

    public CustomerResult getCustomerResult() {
        return customerResult;
    }

    public AnswerVo getAnswerVo() {
        return answerVo;
    }
}
