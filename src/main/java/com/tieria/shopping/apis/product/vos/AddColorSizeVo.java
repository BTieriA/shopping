package com.tieria.shopping.apis.product.vos;

public class AddColorSizeVo {
    private final String color;
    private final String size;
    private final int itemIndex;
    private final int imgIndex;
    private final int userIndex;

    public AddColorSizeVo(String color, String size, int itemIndex, int imgIndex, int userIndex) {
        this.color = color;
        this.size = size;
        this.itemIndex = itemIndex;
        this.imgIndex = imgIndex;
        this.userIndex = userIndex;
    }

    public String getColor() {
        return color;
    }

    public String getSize() {
        return size;
    }

    public int getItemIndex() {
        return itemIndex;
    }

    public int getImgIndex() {
        return imgIndex;
    }

    public int getUserIndex() {
        return userIndex;
    }
}
