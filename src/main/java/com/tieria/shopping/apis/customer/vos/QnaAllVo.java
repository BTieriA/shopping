package com.tieria.shopping.apis.customer.vos;

import java.sql.Timestamp;

public class QnaAllVo {
    private final int qnaIndex;
    private final String qnaTitle;
    private final String qnaContent;
    private final Timestamp qnaDate;
    private final String userName;
    private final int ansIndex;

    public QnaAllVo(int qnaIndex, String qnaTitle, String qnaContent, Timestamp qnaDate, String userName, int ansIndex) {
        this.qnaIndex = qnaIndex;
        this.qnaTitle = qnaTitle;
        this.qnaContent = qnaContent;
        this.qnaDate = qnaDate;
        this.userName = userName;
        this.ansIndex = ansIndex;
    }

    public int getQnaIndex() {
        return qnaIndex;
    }

    public String getQnaTitle() {
        return qnaTitle;
    }

    public String getQnaContent() {
        return qnaContent;
    }

    public Timestamp getQnaDate() {
        return qnaDate;
    }

    public String getUserName() {
        return userName;
    }

    public int getAnsIndex() {
        return ansIndex;
    }
}
