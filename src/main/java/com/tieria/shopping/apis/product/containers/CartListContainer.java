package com.tieria.shopping.apis.product.containers;

import com.tieria.shopping.apis.product.enums.CartResult;
import com.tieria.shopping.apis.product.vos.CartVo;

import java.util.ArrayList;

public class CartListContainer {
    private final CartResult cartResult;
    private final ArrayList<CartVo> cartList;

    public CartListContainer(CartResult cartResult) {
        this.cartResult = cartResult;
        this.cartList = null;
    }

    public CartListContainer(CartResult cartResult, ArrayList<CartVo> cartList) {
        this.cartResult = cartResult;
        this.cartList = cartList;
    }

    public CartResult getCartResult() {
        return cartResult;
    }

    public ArrayList<CartVo> getCartList() {
        return cartList;
    }
}
