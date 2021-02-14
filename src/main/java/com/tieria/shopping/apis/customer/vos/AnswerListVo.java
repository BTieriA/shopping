package com.tieria.shopping.apis.customer.vos;

import java.sql.Timestamp;

public class AnswerListVo {
    private final int qnaIndex;
    private final String ansContent;
    private final String userName;
    private final Timestamp asnDate;

    public AnswerListVo(int qnaIndex, String ansContent, String userName, Timestamp asnDate) {
        this.qnaIndex = qnaIndex;
        this.ansContent = ansContent;
        this.userName = userName;
        this.asnDate = asnDate;
    }

    public int getQnaIndex() {
        return qnaIndex;
    }

    public String getAnsContent() {
        return ansContent;
    }

    public String getUserName() {
        return userName;
    }

    public Timestamp getAsnDate() {
        return asnDate;
    }
}
