package com.tieria.shopping.apis.customer.vos;

public class UpdateAnsVo {
    private final int index;
    private final String content;

    public UpdateAnsVo(int index, String content) {
        this.index = index;
        this.content = content;
    }

    public int getIndex() {
        return index;
    }

    public String getContent() {
        return content;
    }
}
