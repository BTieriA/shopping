package com.tieria.shopping.apis.product.vos;

public class AddCartVo {
    private final int subTotal;
    private final int delivery;
    private final int sumTotal;
    private final int userIndex;
    private final int itemIndex;
    private final int detailIndex;

    public AddCartVo(int subTotal, int delivery, int sumTotal, int userIndex, int itemIndex, int detailIndex) {
        this.subTotal = subTotal;
        this.delivery = delivery;
        this.sumTotal = sumTotal;
        this.userIndex = userIndex;
        this.itemIndex = itemIndex;
        this.detailIndex = detailIndex;
    }

    public int getSubTotal() {
        return subTotal;
    }

    public int getDelivery() {
        return delivery;
    }

    public int getSumTotal() {
        return sumTotal;
    }

    public int getUserIndex() {
        return userIndex;
    }

    public int getItemIndex() {
        return itemIndex;
    }

    public int getDetailIndex() {
        return detailIndex;
    }
}
