package com.tieria.shopping.apis.product.containers;

import com.tieria.shopping.apis.product.enums.CartResult;
import com.tieria.shopping.apis.product.vos.CartTotalVo;
import com.tieria.shopping.apis.product.vos.CartVo;

import java.util.ArrayList;

public class CartTotalListContainer {
    private final CartResult cartResult;
    private final ArrayList<CartTotalVo> cartList;

    public CartTotalListContainer(CartResult cartResult) {
        this.cartResult = cartResult;
        this.cartList = null;
    }

    public CartTotalListContainer(CartResult cartResult, ArrayList<CartTotalVo> cartList) {
        this.cartResult = cartResult;
        this.cartList = cartList;
    }

    public CartResult getCartResult() {
        return cartResult;
    }

    public ArrayList<CartTotalVo> getCartList() {
        return cartList;
    }
}
