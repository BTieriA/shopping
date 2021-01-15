package com.tieria.shopping.apis.product.vos;

public class AddImageVo {
    private final int productIndex;
    private final String imageName;
    private final byte[] imageData;

    public AddImageVo(int productIndex, String imageName, byte[] imageData) {
        this.productIndex = productIndex;
        this.imageName = imageName;
        this.imageData = imageData;
    }

    public int getProductIndex() {
        return productIndex;
    }

    public String getImageName() {
        return imageName;
    }

    public byte[] getImageData() {
        return imageData;
    }
}
