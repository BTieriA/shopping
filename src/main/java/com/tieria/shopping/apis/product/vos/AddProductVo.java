package com.tieria.shopping.apis.product.vos;


public class AddProductVo {
    private final String pdtBrand;
    private final String pdtName;
    private final int pdtPrice;
    private final int pdtKinds;
    private final String pdtDetail;
    private final String pdtImage;

    public AddProductVo(String pdtBrand, String pdtName, int pdtPrice, int pdtKinds, String pdtDetail, String pdtImage) {
        this.pdtBrand = pdtBrand;
        this.pdtName = pdtName;
        this.pdtPrice = pdtPrice;
        this.pdtKinds = pdtKinds;
        this.pdtDetail = pdtDetail;
        this.pdtImage = pdtImage;
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

    public String getPdtImage() {
        return pdtImage;
    }
}
