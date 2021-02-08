let content = window.document.querySelector('.js-admin');
let items = window.document.querySelector('.js-items');
let details = window.document.querySelector('.js-addition');
let cart = window.document.querySelector('.js-cart');
let qna = window.document.querySelector('.js-qna');

// Ajax - Go to Home
let homeClick = () => {
    if (!content.classList.contains('visible')){
        content.classList.add('visible');
    }
    if (items.classList.contains('visible')) {
        items.classList.remove('visible');
    }
    if (details.classList.contains('visible')) {
        details.classList.remove('visible');
    }
    if (cart.classList.contains('visible')) {
        cart.classList.remove('visible');
    }
    if (qna.classList.contains('visible')) {
        qna.classList.remove('visible');
    }
};


// Ajax - Go to Products
let adminItemsClick = (index) => {
    if (content.classList.contains('visible')){
        content.classList.remove('visible');
    }
    if (!items.classList.contains('visible')) {
        items.classList.add('visible');
    }
    if (details.classList.contains('visible')) {
        details.classList.remove('visible');
    }
    if (cart.classList.contains('visible')) {
        cart.classList.remove('visible');
    }
    if (qna.classList.contains('visible')) {
        qna.classList.remove('visible');
    }

    let callback = (responseText) => {
        items.innerHTML = responseText;
        // SortList.sortListData();
        AdminSortList.sortListData(index);
    };
    let fallback = () => {
        Dialog.show('상품', '상품이 없습니다.', ['확인'], [() => {
            Dialog.hide();
        }]);
    };

    Ajax.request('GET', 'parts/adminItems.html', callback, fallback);
};

// Category Check Box
let checkClick = (kinds) => {
    List.listData(kinds);
};

// Ajax - Go to Detail
let adminDetailClick = (index) => {
    if (content.classList.contains('visible')){
        content.classList.remove('visible');
    }
    if (items.classList.contains('visible')) {
        items.classList.remove('visible');
    }
    if (!details.classList.contains('visible')) {
        details.classList.add('visible');
    }
    if (cart.classList.contains('visible')) {
        cart.classList.remove('visible');
    }
    if (qna.classList.contains('visible')) {
        qna.classList.remove('visible');
    }

    let callback = (responseText) => {
        items.innerHTML = responseText;
        AdminUpdate.updateItem(index);
    };

    let fallback = () => {
        Dialog.show('상세정보', '해당 상품이 없습니다.', ['확인'], [() => {
            window.location.href = "main";
            Dialog.hide();
        }]);

    };
    Ajax.request('GET', 'parts/adminUpdate.html', callback, fallback);
};

// Ajax - Go to Cart
let addDetailClick = (index) => {
    if (content.classList.contains('visible')){
        content.classList.remove('visible');
    }
    if (items.classList.contains('visible')) {
        items.classList.remove('visible');
    }
    if (details.classList.contains('visible')) {
        details.classList.remove('visible');
    }
    if (!cart.classList.contains('visible')) {
        cart.classList.add('visible');
    }
    if (qna.classList.contains('visible')) {
        qna.classList.remove('visible');
    }

    let callback = (responseText) => {
        items.innerHTML = responseText;
        Cart.cartData(index);
        window.location.href = "main"
    };
    let fallback = () => {
        Dialog.show('구매', '구매한 제품이 없습니다.', ['확인'], [() => {
            Dialog.hide();
        }]);
    };
    let formData = new FormData();
    formData.append("index", index);
    Ajax.request('GET', 'parts/cart.html', callback, fallback, formData);
};


// Ajax - Go to Brand
let brandClick = (kinds, brand) => {
    if (content.classList.contains('visible')){
        content.classList.remove('visible');
    }
    if (!items.classList.contains('visible')) {
        items.classList.add('visible');
    }
    if (details.classList.contains('visible')) {
        details.classList.remove('visible');
    }
    if (cart.classList.contains('visible')) {
        cart.classList.remove('visible');
    }
    if (qna.classList.contains('visible')) {
        qna.classList.remove('visible');
    }

    let callback = (responseText) => {
        items.innerHTML = responseText;
        Brand.brandListData(kinds, brand)
    };
    let fallback = () => {
        Dialog.show('상품', '상품이 없습니다.', ['확인'], [() => {
            Dialog.hide();
        }]);
    };
    Ajax.request('GET', 'parts/items.html', callback, fallback);
};


// Ajax - Go to Cart History
let historyClick = () => {
    if (content.classList.contains('visible')){
        content.classList.remove('visible');
    }
    if (items.classList.contains('visible')) {
        items.classList.remove('visible');
    }
    if (details.classList.contains('visible')) {
        details.classList.remove('visible');
    }
    if (!cart.classList.contains('visible')) {
        cart.classList.add('visible');
    }
    if (qna.classList.contains('visible')) {
        qna.classList.remove('visible');
    }

    let callback = (responseText) => {
        items.innerHTML = responseText;
        CartHistory.cartHistoryData();
    };
    let fallback = () => {
        Dialog.show('구매', '구매한 제품이 없습니다.', ['확인'], [() => {
            Dialog.hide();
        }]);
    };

    Ajax.request('GET', 'parts/cart.html', callback, fallback);
};


// Ajax - Go to Qna
let qnaClick = () => {
    if (content.classList.contains('visible')){
        content.classList.remove('visible');
    }
    if (items.classList.contains('visible')) {
        items.classList.remove('visible');
    }
    if (details.classList.contains('visible')) {
        details.classList.remove('visible');
    }
    if (cart.classList.contains('visible')) {
        cart.classList.remove('visible');
    }
    if (!qna.classList.contains('visible')) {
        qna.classList.add('visible');
    }

    let callback = (responseText) => {
        qna.innerHTML = responseText;
        let formQna = window.document.querySelector('#formQna');
        formQna.onsubmit = () => {
            const callback = (response) => {
                let json = JSON.parse(response);
                if(json['result'] === 'success'){
                    Dialog.show('QNA', '문의사항이 등록되었습니다.', ['확인'], [() => {
                        Dialog.hide();
                    }]);
                } else {
                    fallback();
                }
            };
            const fallback = () => {
                Dialog.show('QNA', '문의사항을 등록하지 못했습니다.', ['확인'], [() => {
                    Dialog.hide();
                }]);
            };
            let formData = new FormData(formQna);
            Ajax.request('POST', '/apis/customer/addQna', callback, fallback, formData);
            return false;
        };
    };
    let fallback = () => {
        Dialog.show('QNA', '고객사항이 없습니다.', ['확인'], [() => {
            Dialog.hide();
        }]);
    };

    Ajax.request('GET', 'parts/qna.html', callback, fallback);
};

//
let qnaListClick = () => {
    if (content.classList.contains('visible')){
        content.classList.remove('visible');
    }
    if (items.classList.contains('visible')) {
        items.classList.remove('visible');
    }
    if (details.classList.contains('visible')) {
        details.classList.remove('visible');
    }
    if (cart.classList.contains('visible')) {
        cart.classList.remove('visible');
    }
    if (!qna.classList.contains('visible')) {
        qna.classList.add('visible');
    }

    let callback = (responseText) => {
        qna.innerHTML = responseText;
        Qna.qnaList();
    };
    let fallback = () => {
        Dialog.show('QNA', '고객사항이 없습니다.', ['확인'], [() => {
            Dialog.hide();
        }]);
    };

    Ajax.request('GET', 'parts/qna-list.html', callback, fallback);
};

// Ajax QNA







