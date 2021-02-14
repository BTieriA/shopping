package com.tieria.shopping.apis.customer.containers;

import com.tieria.shopping.apis.customer.enums.CustomerResult;
import com.tieria.shopping.apis.customer.vos.AnswerListVo;

import java.util.ArrayList;

public class AnsListResultContainer {
    private final CustomerResult customerResult;
    private final ArrayList<AnswerListVo> ansList;

    public AnsListResultContainer(CustomerResult customerResult, ArrayList<AnswerListVo> ansList) {
        this.customerResult = customerResult;
        this.ansList = ansList;
    }

    public AnsListResultContainer(CustomerResult customerResult) {
        this.customerResult = customerResult;
        this.ansList = null;
    }

    public CustomerResult getCustomerResult() {
        return customerResult;
    }

    public ArrayList<AnswerListVo> getAnsList() {
        return ansList;
    }
}
