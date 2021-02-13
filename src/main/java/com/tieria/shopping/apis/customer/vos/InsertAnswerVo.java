package com.tieria.shopping.apis.customer.vos;

import java.sql.Timestamp;

public class InsertAnswerVo {
    private final String ansContent;
    private final int userIndex;

    public InsertAnswerVo(String ansContent, int userIndex) {
        this.ansContent = ansContent;
        this.userIndex = userIndex;
    }

    public String getAnsContent() {
        return ansContent;
    }

    public int getUserIndex() {
        return userIndex;
    }
}
