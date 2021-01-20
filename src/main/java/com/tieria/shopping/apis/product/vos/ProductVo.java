package com.tieria.shopping.apis.product.vos;


import java.util.Date;

public class ProductVo {
    private final int pdtIndex;
    private final String pdtBrand;
    private final String pdtName;
    private final int pdtPrice;
    private final int pdtKinds;
    private final String pdtDetail;
    private final Date pdtDate;
    private final String pdtImage;

    public ProductVo(int pdtIndex, String pdtBrand, String pdtName, int pdtPrice, int pdtKinds, String pdtDetail, Date pdtDate, String pdtImage) {
        this.pdtIndex = pdtIndex;
        this.pdtBrand = pdtBrand;
        this.pdtName = pdtName;
        this.pdtPrice = pdtPrice;
        this.pdtKinds = pdtKinds;
        this.pdtDetail = pdtDetail;
        this.pdtDate = pdtDate;
        this.pdtImage = pdtImage;
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

    public Date getPdtDate() {
        return pdtDate;
    }

    public String getPdtImage() {
        return pdtImage;
    }
}
