package com.tieria.shopping.apis.product.vos;


public class ProductVo {
    private final int pdtIndex;
    private final String pdtRoom;
    private final String pdtName;
    private final int pdtPrice;
    private final String pdtSize;
    private final String pdtDetail;
    private final String pdtImage;

    public ProductVo(int pdtIndex, String pdtRoom, String pdtName, int pdtPrice, String pdtSize, String pdtDetail, String pdtImage) {
        this.pdtIndex = pdtIndex;
        this.pdtRoom = pdtRoom;
        this.pdtName = pdtName;
        this.pdtPrice = pdtPrice;
        this.pdtSize = pdtSize;
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

    public String getPdtSize() {
        return pdtSize;
    }

    public String getPdtDetail() {
        return pdtDetail;
    }

    public String getPdtImage() {
        return pdtImage;
    }
}
