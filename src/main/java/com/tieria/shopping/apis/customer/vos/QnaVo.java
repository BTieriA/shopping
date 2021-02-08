package com.tieria.shopping.apis.customer.vos;

import java.sql.Timestamp;

public class QnaVo {
    private final int customerIndex;
    private final String customerTitle;
    private final String customerContent;
    private final Timestamp customerDate;
    private final String customerName;

    public QnaVo(int customerIndex, String customerTitle, String customerContent, Timestamp customerDate, String customerName) {
        this.customerIndex = customerIndex;
        this.customerTitle = customerTitle;
        this.customerContent = customerContent;
        this.customerDate = customerDate;
        this.customerName = customerName;
    }

    public int getCustomerIndex() {
        return customerIndex;
    }

    public String getCustomerTitle() {
        return customerTitle;
    }

    public String getCustomerContent() {
        return customerContent;
    }

    public Timestamp getCustomerDate() {
        return customerDate;
    }

    public String getCustomerName() {
        return customerName;
    }
}


