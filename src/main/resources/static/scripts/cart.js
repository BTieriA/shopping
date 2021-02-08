class Cart {
    static cartData = (index) => {
        // cart table
        let cart = window.document.querySelector('.js-cart');
        let cartContainer = document.createElement('div');
        let cartContainerTable = document.createElement('table');
        let cartContainerTableTitle = document.createElement('tr');
        let cartContainerTableTitleProduct = document.createElement('th');
        let cartContainerTableTitleQuantity = document.createElement('th');
        let cartContainerTableTitleSubtotal = document.createElement('th');

        // total
        let cartContainerTotal = document.createElement('form');
        let cartContainerTotalTable = document.createElement('table');
        let cartContainerTotalTableSubTr = document.createElement('tr');
        let cartContainerTotalTableSubTrSubTotal = document.createElement('td');
        let cartContainerTotalTableSubTrPrice = document.createElement('td');
        let cartContainerTotalTableDeliverTr = document.createElement('tr');
        let cartContainerTotalTableDeliverTrDelivery = document.createElement('td');
        let cartContainerTotalTableDeliverTrPrice = document.createElement('td');
        let cartContainerTotalTableTotalTr = document.createElement('tr');
        let cartContainerTotalTableTotalTrSum = document.createElement('td');
        let cartContainerTotalTableTotalTrPrice = document.createElement('td');
        let cartContainerTotalButton = document.createElement('a');
        let subTotal = 0;
        let count = 0;

        // cart table
        cart.innerHTML = '';
        cart.append(cartContainer);
        cartContainer.append(cartContainerTable);
        cartContainerTable.append(cartContainerTableTitle);
        cartContainerTableTitle.append(cartContainerTableTitleProduct);
        cartContainerTableTitle.append(cartContainerTableTitleQuantity);
        cartContainerTableTitle.append(cartContainerTableTitleSubtotal);

        const callback = (response) => {
            let json = JSON.parse(response);
            let product = json['cartList'];

            if (product != null) {
                for (let i = 0; i < product.length; i++) {

                    // add cart
                    let cartContainerTableContent = document.createElement('tr');
                    let cartContainerTableContentTd = document.createElement('td');
                    let cartContainerTableContentTdInfo = document.createElement('form');
                    let cartContainerTableContentTdInfoImg = document.createElement('img');
                    let cartContainerTableContentTdInfoDetail = document.createElement('div');
                    let cartContainerTableContentTdInfoDetailName = document.createElement('p');
                    let cartContainerTableContentTdInfoDetailBrand = document.createElement('span');
                    let cartContainerTableContentTdInfoDetailBr = document.createElement('br');
                    let cartContainerTableContentTdInfoDetailForm = document.createElement('form');
                    let cartContainerTableContentTdInfoDetailRemove = document.createElement('a');
                    let cartContainerTableContentDetailTd = document.createElement('td');
                    let cartContainerTableContentDetailTdBox = document.createElement('div');
                    let cartContainerTableContentDetailTdBoxColor = document.createElement('div');
                    let cartContainerTableContentDetailTdBoxData = document.createElement('div');
                    let cartContainerTableContentDetailTdBoxDataColor = document.createElement('p');
                    let cartContainerTableContentDetailTdBoxDataBr = document.createElement('br');
                    let cartContainerTableContentDetailTdBoxDataSize = document.createElement('p');
                    let cartContainerTableContentTotal = document.createElement('td');

                    // add cart
                    cartContainerTable.append(cartContainerTableContent);
                    cartContainerTableContent.append(cartContainerTableContentTd);
                    cartContainerTableContentTd.append(cartContainerTableContentTdInfo);
                    cartContainerTableContentTdInfo.append(cartContainerTableContentTdInfoImg);
                    cartContainerTableContentTdInfo.append(cartContainerTableContentTdInfoDetail);
                    cartContainerTableContentTdInfoDetail.append(cartContainerTableContentTdInfoDetailName);
                    cartContainerTableContentTdInfoDetail.append(cartContainerTableContentTdInfoDetailBrand);
                    cartContainerTableContentTdInfoDetail.append(cartContainerTableContentTdInfoDetailBr);
                    cartContainerTableContentTdInfoDetail.append(cartContainerTableContentTdInfoDetailForm);
                    cartContainerTableContentTdInfoDetailForm.append(cartContainerTableContentTdInfoDetailRemove);
                    cartContainerTableContent.append(cartContainerTableContentDetailTd);
                    cartContainerTableContentDetailTd.append(cartContainerTableContentDetailTdBox);
                    cartContainerTableContentDetailTdBox.append(cartContainerTableContentDetailTdBoxColor);
                    cartContainerTableContentDetailTdBox.append(cartContainerTableContentDetailTdBoxData);
                    cartContainerTableContentDetailTdBoxData.append(cartContainerTableContentDetailTdBoxDataColor);
                    cartContainerTableContentDetailTdBoxData.append(cartContainerTableContentDetailTdBoxDataBr);
                    cartContainerTableContentDetailTdBoxData.append(cartContainerTableContentDetailTdBoxDataSize);
                    cartContainerTableContent.append(cartContainerTableContentTotal);

                    cartContainerTableContent.classList.add('item-row');
                    cartContainerTableContent.classList.remove('hidden');
                    cartContainerTableContentTdInfo.classList.add('cart-info');
                    cartContainerTableContentTdInfoImg.src = '/apis/product/imageList?index=' + product[i][`itemIndex`];
                    cartContainerTableContentTdInfoDetailName.innerHTML = product[i]['itemName'];
                    cartContainerTableContentTdInfoDetailBrand.innerHTML = product[i]['itemBrand'];
                    cartContainerTableContentTdInfoDetailForm.action = '/apis/product/deleteCart';
                    cartContainerTableContentTdInfoDetailForm.name = 'deleteCart';
                    cartContainerTableContentTdInfoDetailRemove.innerHTML = 'remove';
                    cartContainerTableContentTdInfoDetailRemove.href = 'javascript:deleteCart.submit();';
                    cartContainerTableContentTdInfoDetailRemove.onclick = () => {
                        const delCallback = (delResponse) => {
                            let jsonDel = JSON.parse(delResponse);
                            if (jsonDel['result'] === 'success') {
                                Dialog.show('장바구니', '상품 구매를 취소하였습니다.', ['확인'], [() => {
                                    Dialog.hide();
                                }]);
                                cartContainerTableContent.classList.add('hidden');
                            } else {
                                delFallback();
                            }
                        };
                        const delFallback = () => {
                            Dialog.show('장바구니', '상품 구매를 취소할 수 없습니다', ['확인'], [() => {
                                Dialog.hide();
                            }]);
                        };
                        let delFormData = new FormData();
                        delFormData.append("itemIndex", product[i]['itemIndex']);
                        Ajax.request('POST', '/apis/product/deleteCart', delCallback, delFallback, delFormData);
                        return false;
                    };
                    cartContainerTableContentDetailTdBox.classList.add('cart-info');
                    cartContainerTableContentDetailTdBoxColor.classList.add('color');
                    cartContainerTableContentDetailTdBoxColor.style.backgroundColor = product[i]['itemColor'];
                    cartContainerTableContentDetailTdBoxDataColor.innerHTML = product[i]['itemColor'];
                    cartContainerTableContentDetailTdBoxDataSize.innerHTML = product[i]['itemSize'];
                    cartContainerTableContentTotal.innerHTML = '₩' + (parseInt(product[i]['itemPrice'])).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");

                    subTotal += product[i]['itemPrice'];
                    count = product.length;
                }
            } else {
                fallback();
            }

            // total
            cartContainer.append(cartContainerTotal);
            cartContainerTotal.append(cartContainerTotalTable);
            cartContainerTotalTable.append(cartContainerTotalTableSubTr);
            cartContainerTotalTableSubTr.append(cartContainerTotalTableSubTrSubTotal);
            cartContainerTotalTableSubTr.append(cartContainerTotalTableSubTrPrice);
            cartContainerTotalTable.append(cartContainerTotalTableDeliverTr);
            cartContainerTotalTableDeliverTr.append(cartContainerTotalTableDeliverTrDelivery);
            cartContainerTotalTableDeliverTr.append(cartContainerTotalTableDeliverTrPrice);
            cartContainerTotalTable.append(cartContainerTotalTableTotalTr);
            cartContainerTotalTableTotalTr.append(cartContainerTotalTableTotalTrSum);
            cartContainerTotalTableTotalTr.append(cartContainerTotalTableTotalTrPrice);
            cartContainerTotal.append(cartContainerTotalButton);

            // table
            cartContainer.classList.add('container');
            cartContainer.classList.add('cart');
            cartContainerTableTitleProduct.innerHTML = 'Product';
            cartContainerTableTitleQuantity.innerHTML = 'Detail';
            cartContainerTableTitleSubtotal.innerHTML = 'Subtotal';

            // total
            cartContainerTotalTableSubTrSubTotal.innerHTML = 'Subtotal';
            cartContainerTotalTableSubTrPrice.innerHTML = '₩' + (subTotal).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
            cartContainerTotalTableDeliverTrDelivery.innerHTML = 'Delivery';
            cartContainerTotalTableDeliverTrPrice.innerHTML = '₩' + (2500 * count).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
            cartContainerTotalTableTotalTrSum.innerHTML = "Total";
            cartContainerTotalTableTotalTrPrice.innerHTML = '₩' + (subTotal + (2500 * count)).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");

            cartContainerTotal.classList.add('total-price');
            cartContainerTotal.name = 'payment';
            cartContainerTotal.action = "/apis/product/deleteAllCart";
            cartContainerTotalButton.classList.add('checkout');
            cartContainerTotalButton.classList.add('btn');
            cartContainerTotalButton.innerHTML = 'Payment';
            cartContainerTotalButton.href = 'javascript:payment.submit();';
            cartContainerTotalButton.onclick = () => {
                const allCallback = (allResponse) => {
                    let jsonAllDel = JSON.parse(allResponse);
                    if (jsonAllDel['result'] === 'success') {
                        Dialog.show('장바구니', '상품 구매를 구매하였습니다.', ['확인'], [() => {
                            Dialog.hide();
                        }]);
                        let cartCount = window.document.querySelector('.count');
                        cartCount.innerHTML = '0';
                    } else {
                        allFallback();
                    }

                };
                const allFallback = () => {
                    Dialog.show('장바구니', '상품 구매를 구매하지 못했습니다.', ['확인'], [() => {
                        Dialog.hide();
                    }]);
                };
                Ajax.request('POST', '/apis/product/deleteAllCart', allCallback, allFallback);
                return false;
            };
            // count
            let cartCount = window.document.querySelector('.count');
            cartCount.innerHTML = count;
        };
        const fallback = () => {
            Dialog.show('장바구니', '아무 상품도 구매하지 않았습니다.', ['확인'], [() => {
                Dialog.hide();
            }]);
        };
        let formData = new FormData();
        formData.append("index", index);
        // cart list 보기 추가
        Ajax.request('POST', "apis/product/colorSize", callback, fallback, formData);
        return false;
    }
}