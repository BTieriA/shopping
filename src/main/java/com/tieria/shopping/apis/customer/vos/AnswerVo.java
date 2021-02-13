package com.tieria.shopping.apis.customer.vos;

import java.sql.Timestamp;

public class AnswerVo {
    private final int ansIndex;
    private final String ansContent;
    private final Timestamp ansDate;
    private final String userName;
    private final int customerIndex;

    public AnswerVo(int ansIndex, String ansContent, Timestamp ansDate, String userName, int customerIndex) {
        this.ansIndex = ansIndex;
        this.ansContent = ansContent;
        this.ansDate = ansDate;
        this.userName = userName;
        this.customerIndex = customerIndex;
    }

    public int getAnsIndex() {
        return ansIndex;
    }

    public String getAnsContent() {
        return ansContent;
    }

    public Timestamp getAnsDate() {
        return ansDate;
    }

    public String getUserName() {
        return userName;
    }

    public int getCustomerIndex() {
        return customerIndex;
    }
}
