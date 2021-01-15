package com.tieria.shopping.apis.product.vos;

public class ImageVo {
    private final int imageIndex;
    private final int productIndex;
    private final String imageName;
    private final byte[] imageData;

    public ImageVo(int imageIndex, int productIndex, String imageName, byte[] imageData) {
        this.imageIndex = imageIndex;
        this.productIndex = productIndex;
        this.imageName = imageName;
        this.imageData = imageData;
    }

    public int getImageIndex() {
        return imageIndex;
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
