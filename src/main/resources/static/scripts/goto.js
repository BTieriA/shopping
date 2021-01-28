// Ajax - Go to Home
let homeClick = () => {
    let content = window.document.querySelector('.js-content');
    let items = window.document.querySelector('.js-items');
    let details = window.document.querySelector('.js-detail');
    let cart = window.document.querySelector('.js-cart');

    content.classList.add('visible');
    items.classList.remove('visible');
    details.classList.remove('visible');
    cart.classList.remove('visible');

};


// Ajax - Go to Products
let productsClick = () => {
    let content = window.document.querySelector('.js-content');
    let items = window.document.querySelector('.js-items');
    let details = window.document.querySelector('.js-detail');
    let cart = window.document.querySelector('.js-cart');

    content.classList.remove('visible');
    items.classList.add('visible');
    details.classList.remove('visible');
    cart.classList.remove('visible');

    let callback = (responseText) => {
        items.innerHTML = responseText;
        List.listData();
    };
    let fallback = () => {
        alert('물품이 없습니다');
    };

    Ajax.request('GET', 'parts/items.html', callback, fallback);
};

// Category Check Box
let checkClick = (kinds) => {
    List.listData(kinds);
};

// Ajax - Go to Detail
let detailClick = (index) => {
    let content = window.document.querySelector('.js-content');
    let items = window.document.querySelector('.js-items');
    let details = window.document.querySelector('.js-detail');
    let cart = window.document.querySelector('.js-cart');

    content.classList.remove('visible');
    items.classList.remove('visible');
    details.classList.add('visible');
    cart.classList.remove('visible');

    let callback = (responseText) => {
        items.innerHTML = responseText;
        Detail.detailData(index);
    };
    let fallback = () => {
        alert('물품이 없습니다');
        window.location.href = "main"
    };
    Ajax.request('GET', 'parts/detail.html', callback, fallback);
};

// Ajax - Go to Cart
let addDetailClick = (index) => {
    let content = window.document.querySelector('.js-content');
    let items = window.document.querySelector('.js-items');
    let details = window.document.querySelector('.js-detail');
    let cart = window.document.querySelector('.js-cart');

    content.classList.remove('visible');
    items.classList.remove('visible');
    details.classList.remove('visible');
    cart.classList.add('visible');

    let callback = (responseText) => {
        items.innerHTML = responseText;
        // 카트 리스트 보기 함수 추가

        Cart.cartData(index);
    };
    let fallback = () => {
        alert('구입한 제품이 없습니다');
    };
    let formData = new FormData();
    formData.append("index", index);
    Ajax.request('GET', 'parts/cart.html', callback, fallback, formData);
};


