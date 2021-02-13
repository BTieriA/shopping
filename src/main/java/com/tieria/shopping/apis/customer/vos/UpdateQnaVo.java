package com.tieria.shopping.apis.customer.vos;

public class UpdateQnaVo {
    private final String qnaTitle;
    private final String qnaContent;

    public UpdateQnaVo(String qnaTitle, String qnaContent) {
        this.qnaTitle = qnaTitle;
        this.qnaContent = qnaContent;
    }

    public String getQnaTitle() {
        return qnaTitle;
    }

    public String getQnaContent() {
        return qnaContent;
    }
}
