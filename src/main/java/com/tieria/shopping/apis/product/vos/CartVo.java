package com.tieria.shopping.apis.product.vos;

import java.sql.Timestamp;

public class CartVo {
    private final int imgIndex;
    private final String itemName;
    private final String itemBrand;
    private final String itemColor;
    private final String itemSize;
    private final int itemPrice;
    private final Timestamp itemDate;

    public CartVo(int imgIndex, String itemName, String itemBrand, String itemColor, String itemSize, int itemPrice, Timestamp itemDate) {
        this.imgIndex = imgIndex;
        this.itemName = itemName;
        this.itemBrand = itemBrand;
        this.itemColor = itemColor;
        this.itemSize = itemSize;
        this.itemPrice = itemPrice;
        this.itemDate = itemDate;
    }

    public int getImgIndex() {
        return imgIndex;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemBrand() {
        return itemBrand;
    }

    public String getItemColor() {
        return itemColor;
    }

    public String getItemSize() {
        return itemSize;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public Timestamp getItemDate() {
        return itemDate;
    }
}
