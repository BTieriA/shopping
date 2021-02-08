package com.tieria.shopping.apis.product.vos;

import java.util.Date;

public class UpdateProductVo {
    private final int pdtIndex;
    private final String pdtBrand;
    private final String pdtName;
    private final int pdtPrice;
    private final int pdtKinds;
    private final String pdtDetail;

    public UpdateProductVo(int pdtIndex, String pdtBrand, String pdtName, int pdtPrice, int pdtKinds, String pdtDetail) {
        this.pdtIndex = pdtIndex;
        this.pdtBrand = pdtBrand;
        this.pdtName = pdtName;
        this.pdtPrice = pdtPrice;
        this.pdtKinds = pdtKinds;
        this.pdtDetail = pdtDetail;
    }

    public int getPdtIndex() {
        return pdtIndex;
    }

    public String getPdtBrand() {
        return pdtBrand;
    }

    public String getPdtName() {
        return pdtName;
    }

    public int getPdtPrice() {
        return pdtPrice;
    }

    public int getPdtKinds() {
        return pdtKinds;
    }

    public String getPdtDetail() {
        return pdtDetail;
    }

}
