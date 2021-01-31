package com.tieria.shopping.apis.product.vos;

public class SortVo {
    private final String sortName;
    private final int desc;

    public SortVo(String sortName, int desc) {
        this.sortName = sortName;
        this.desc = desc;
    }

    public String getSortName() {
        return sortName;
    }

    public int getDesc() {
        return desc;
    }
}
