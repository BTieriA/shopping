package com.tieria.shopping.common;

public class DetailVo {
    private final int detailIndex;
    private final String color;
    private final String size;
    private final int itemIndex;
    private final int imageIndex;

    public DetailVo(int detailIndex, String color, String size, int itemIndex, int imageIndex) {
        this.detailIndex = detailIndex;
        this.color = color;
        this.size = size;
        this.itemIndex = itemIndex;
        this.imageIndex = imageIndex;
    }

    public int getDetailIndex() {
        return detailIndex;
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

    public int getImageIndex() {
        return imageIndex;
    }
}
