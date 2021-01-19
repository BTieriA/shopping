package com.tieria.shopping.apis.product.vos;


public class ProductVo {
    private final int pdtIndex;
    private final String pdtRoom;
    private final String pdtName;
    private final int pdtPrice;
    private final int pdtKinds;
    private final String pdtDetail;
    private final String pdtImage;

    public ProductVo(int pdtIndex, String pdtRoom, String pdtName, int pdtPrice, int pdtKinds, String pdtDetail, String pdtImage) {
        this.pdtIndex = pdtIndex;
        this.pdtRoom = pdtRoom;
        this.pdtName = pdtName;
        this.pdtPrice = pdtPrice;
        this.pdtKinds = pdtKinds;
        this.pdtDetail = pdtDetail;
        this.pdtImage = pdtImage;
    }

    public int getPdtIndex() {
        return pdtIndex;
    }

    public String getPdtRoom() {
        return pdtRoom;
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
