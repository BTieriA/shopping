class AdminCartHistory {
    static adminCartHistoryData = () => {
        let cartList = (page) => {
            // cart table
            let cart = window.document.querySelector('.js-cart');
            let cartContainer = document.createElement('div');
            let cartContainerTable = document.createElement('table');
            let cartContainerTableTitle = document.createElement('tr');
            let cartContainerTableTitleProduct = document.createElement('th');
            let cartContainerTableTitleName = document.createElement('th');
            let cartContainerTableTitleQuantity = document.createElement('th');
            let cartContainerTableTitleSubtotal = document.createElement('th');

            // cart table
            cart.innerHTML = '';
            cart.append(cartContainer);
            cartContainer.append(cartContainerTable);
            cartContainerTable.append(cartContainerTableTitle);
            cartContainerTableTitle.append(cartContainerTableTitleProduct);
            cartContainerTableTitle.append(cartContainerTableTitleName);
            cartContainerTableTitle.append(cartContainerTableTitleQuantity);
            cartContainerTableTitle.append(cartContainerTableTitleSubtotal);
            cartContainer.classList.add('container');
            cartContainer.classList.add('cart');
            cartContainerTableTitleProduct.innerHTML = 'Product';
            cartContainerTableTitleName.innerHTML = 'Name';
            cartContainerTableTitleQuantity.innerHTML = 'Detail';
            cartContainerTableTitleSubtotal.innerHTML = 'Subtotal';

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
                        let cartContainerTableContentTdInfoDetailDate = document.createElement('span');
                        let cartContainerTableContentNameTd = document.createElement('td');
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
                        cartContainerTableContent.append(cartContainerTableContentNameTd);
                        cartContainerTableContentTdInfo.append(cartContainerTableContentTdInfoDetail);
                        cartContainerTableContentTdInfoDetail.append(cartContainerTableContentTdInfoDetailName);
                        cartContainerTableContentTdInfoDetail.append(cartContainerTableContentTdInfoDetailBrand);
                        cartContainerTableContentTdInfoDetail.append(cartContainerTableContentTdInfoDetailBr);
                        cartContainerTableContentTdInfoDetail.append(cartContainerTableContentTdInfoDetailDate);
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
                        cartContainerTableContentTdInfoDetailDate.classList.add('date');
                        cartContainerTableContentTdInfoDetailDate.innerHTML = product[i]['itemDate'];
                        cartContainerTableContentNameTd.innerHTML = product[i]['userName'];
                        cartContainerTableContentDetailTdBox.classList.add('cart-info');
                        cartContainerTableContentDetailTdBoxColor.classList.add('color');
                        cartContainerTableContentDetailTdBoxColor.style.backgroundColor = product[i]['itemColor'];
                        cartContainerTableContentDetailTdBoxDataColor.innerHTML = product[i]['itemColor'];
                        cartContainerTableContentDetailTdBoxDataSize.innerHTML = product[i]['itemSize'];
                        cartContainerTableContentTotal.innerHTML = '₩' + (parseInt(product[i]['itemPrice'])).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");

                    }

                    // Pagination
                    let cartContainerPageElement = document.createElement('ul');
                    cartContainerPageElement.classList.add('pagination');
                    cartContainerPageElement.innerHTML = '';

                    let cartContainerPageSpanFirstElement = document.createElement('span');
                    let cartContainerPageSpanPreElement = document.createElement('span');
                    let cartContainerPageSpanNextElement = document.createElement('span');
                    let cartContainerPageSpanLastElement = document.createElement('span');

                    cartContainer.append(cartContainerPageElement);
                    cartContainerPageSpanFirstElement.classList.add('first');
                    cartContainerPageSpanPreElement.classList.add('icon');
                    cartContainerPageSpanNextElement.classList.add('icon');
                    cartContainerPageSpanLastElement.classList.add('last');

                    let startPage = parseInt(json['start_page']);
                    let endPage = parseInt(json['end_page']);
                    let reqPage = parseInt(json['page']);
                    let maxPage = parseInt(json['max_page']);

                    if (reqPage > 1) {
                        cartContainerPageSpanFirstElement.addEventListener('click', () => {
                            cartList(1);
                        });
                        cartContainerPageSpanPreElement.addEventListener('click', () => {
                            cartList(reqPage - 1);
                        });
                        cartContainerPageSpanFirstElement.innerHTML = '<< first';
                        cartContainerPageSpanPreElement.innerHTML = '<';
                        cartContainerPageElement.append(cartContainerPageSpanFirstElement);
                        cartContainerPageElement.append(cartContainerPageSpanPreElement);

                    }

                    for (let i = startPage; i <= endPage; i++) {
                        let cartContainerPageSpanPageElement = document.createElement('span');
                        cartContainerPageSpanPageElement.innerHTML = `${i}`;
                        if (i === reqPage) {
                            cartContainerPageSpanPageElement.classList.add('selected');
                        } else {
                            cartContainerPageSpanPageElement.addEventListener('click', () => {
                                cartList(i);
                            })
                        }
                        cartContainerPageElement.append(cartContainerPageSpanPageElement);
                    }
                    if (reqPage < maxPage) {
                        cartContainerPageSpanNextElement.addEventListener('click', () => {
                            cartList(reqPage + 1);
                        });
                        cartContainerPageSpanLastElement.addEventListener('click', () => {
                            cartList(maxPage);
                        });
                        cartContainerPageSpanNextElement.innerHTML = '>';
                        cartContainerPageSpanLastElement.innerHTML = 'last >>';
                        cartContainerPageElement.append(cartContainerPageSpanNextElement);
                        cartContainerPageElement.append(cartContainerPageSpanLastElement);
                    }
                } else {
                    fallback();
                }

            };
            const fallback = () => {
                Dialog.show('장바구니', '아무 상품도 구매하지 않았습니다.', ['확인'], [() => {
                    Dialog.hide();
                }]);
            };

            let formData = new FormData();
            formData.append('page', page);
            Ajax.request('POST', "apis/product/total-history", callback, fallback, formData);
            return false;
        };
        cartList(1);
    };
}