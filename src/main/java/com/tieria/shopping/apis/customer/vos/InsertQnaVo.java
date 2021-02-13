package com.tieria.shopping.apis.customer.vos;

public class InsertQnaVo {
    private final String title;
    private final String content;
    private final int userIndex;

    public InsertQnaVo(String title, String content, int userIndex) {
        this.title = title;
        this.content = content;
        this.userIndex = userIndex;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public int getUserIndex() {
        return userIndex;
    }
}
