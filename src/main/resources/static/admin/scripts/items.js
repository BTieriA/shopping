// input
let items = window.document.querySelector('.js-items');
let addForm = window.document.querySelector('#addForm');
addForm.onsubmit = () => {
    const callback = (response) => {
        let json = JSON.parse(response);
        if(json['product_input'] === 'success'){
            alert('가구가 등록되었습니다');
            addBox.classList.remove('visible');
            itemListClick();
            items.classList.add('visible');
        } else {
            fallback();
        }
    };
    const fallback = () => {
        alert('등록에 실패하였습니다');
    };

    let formData = new FormData(addForm);

    formData.append("imageFile", imgUpload.files[0]);
    Ajax.request('POST','apis/product/addProduct', callback, fallback, formData);
    return false;
};

// list - Total
// let itemListClick = () => {
//     let itemSection = items.querySelector('.products');
//     let itemSectionLayout = itemSection.querySelector('.products-layout');
//     let itemSectionLayoutCol = itemSectionLayout.querySelector('.col-3-of-4');
//     let itemSectionLayoutColLayout = itemSectionLayoutCol.querySelector('.product-layout');
//
//     itemSectionLayoutColLayout.innerHTML = '';
//     const callback = (response) => {
//         let jsAdmin = window.document.querySelector('.js-admin');
//         let jsAddition = window.document.querySelector('.js-addition');
//         jsAdmin.classList.remove('visible');
//         jsAddition.classList.remove('visible');
//         items.classList.add('visible');
//
//
//         let json = JSON.parse(response);
//         let products = json['products'];
//
//         for (let i = 0; i < products.length; i++) {
//
//             let productElement = document.createElement('div');
//             let productContainerElement = window.document.createElement('div');
//             let productContainerLinkElement = window.document.createElement('a');
//             let productContainerLinkImageElement = window.document.createElement('img');
//             let productContainerCartElement = window.document.createElement('div');
//             let productContainerCartIconElement = window.document.createElement('i');
//             let productContainerSideElement = window.document.createElement('ul');
//             let productContainerSideSpanElement = window.document.createElement('span');
//             let productContainerSideSpanIconElement = window.document.createElement('i');
//             let productBottomElement = window.document.createElement('div');
//             let productBottomLinkElement = window.document.createElement('a');
//             let productBottomPriceElement = window.document.createElement('div');
//             let productBottomPriceSpanElement = window.document.createElement('span');
//
//             productElement.append(productContainerElement);
//             productContainerElement.append(productContainerLinkElement);
//             productContainerLinkElement.append(productContainerLinkImageElement);
//             productContainerElement.append(productContainerCartElement);
//             productContainerCartElement.append(productContainerCartIconElement);
//             productContainerElement.append(productContainerSideElement);
//             productContainerSideElement.append(productContainerSideSpanElement);
//             productContainerSideSpanElement.append(productContainerSideSpanIconElement);
//             productElement.append(productBottomElement);
//             productBottomElement.append(productBottomLinkElement);
//             productBottomElement.append(productBottomPriceElement);
//             productBottomPriceElement.append(productBottomPriceSpanElement);
//
//             productElement.classList.add('product');
//             productContainerElement.classList.add('img-container');
//
//             productContainerLinkImageElement.src =  "/apis/product/imageList?index=" + products[i]['itemIndex'];
//             productContainerCartElement.classList.add('addCart');
//             productContainerCartIconElement.classList.add('fas');
//             productContainerCartIconElement.classList.add('fa-shopping-cart');
//             productContainerSideElement.classList.add('side-icons');
//             productContainerSideSpanIconElement.classList.add('fas');
//             productContainerSideSpanIconElement.classList.add('fa-search');
//             productBottomElement.classList.add('bottom');
//             productBottomLinkElement.innerHTML = products[i]['itemName'];
//             productBottomPriceElement.classList.add('price');
//             productBottomPriceSpanElement.innerHTML = '₩'
//                 + products[i]['itemPrice'].toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
//
//             itemSectionLayoutColLayout.append(productElement);
//         }
//     };
//
//     const fallback = () => {
//         alert('데이터가 없습니다');
//     };
//
//     Ajax.request('POST', 'apis/product/productList', callback, fallback);
//     return false;
// };

// checkbox - category
let checkCategory = (kinds) => {
    let itemSection = items.querySelector('.products');
    let itemSectionLayout = itemSection.querySelector('.products-layout');
    let itemSectionLayoutCol = itemSectionLayout.querySelector('.col-3-of-4');
    let itemSectionLayoutColLayout = itemSectionLayoutCol.querySelector('.product-layout');
    let check = document.getElementsByName("cateKinds");

    itemSectionLayoutColLayout.innerHTML = '';
    const callback = (response) => {
        let jsAdmin = window.document.querySelector('.js-admin');
        let jsAddition = window.document.querySelector('.js-addition');
        jsAdmin.classList.remove('visible');
        jsAddition.classList.remove('visible');
        items.classList.add('visible');

        let json = JSON.parse(response);
        let products = json['products'];

        for (let i = 0; i < products.length; i++) {

            let productElement = document.createElement('div');
            let productContainerElement = window.document.createElement('div');
            let productContainerLinkElement = window.document.createElement('a');
            let productContainerLinkImageElement = window.document.createElement('img');
            let productContainerCartElement = window.document.createElement('div');
            let productContainerCartIconElement = window.document.createElement('i');
            let productContainerSideElement = window.document.createElement('ul');
            let productContainerSideSpanElement = window.document.createElement('span');
            let productContainerSideSpanIconElement = window.document.createElement('i');
            let productBottomElement = window.document.createElement('div');
            let productBottomLinkElement = window.document.createElement('a');
            let productBottomPriceElement = window.document.createElement('div');
            let productBottomPriceSpanElement = window.document.createElement('span');

            productElement.append(productContainerElement);
            productContainerElement.append(productContainerLinkElement);
            productContainerLinkElement.append(productContainerLinkImageElement);
            productContainerElement.append(productContainerCartElement);
            productContainerCartElement.append(productContainerCartIconElement);
            productContainerElement.append(productContainerSideElement);
            productContainerSideElement.append(productContainerSideSpanElement);
            productContainerSideSpanElement.append(productContainerSideSpanIconElement);
            productElement.append(productBottomElement);
            productBottomElement.append(productBottomLinkElement);
            productBottomElement.append(productBottomPriceElement);
            productBottomPriceElement.append(productBottomPriceSpanElement);

            productElement.classList.add('product');
            productContainerElement.classList.add('img-container');

            productContainerLinkImageElement.src =  "/apis/product/imageList?index=" + products[i]['itemIndex'];
            productContainerCartElement.classList.add('addCart');
            productContainerCartIconElement.classList.add('fas');
            productContainerCartIconElement.classList.add('fa-shopping-cart');
            productContainerSideElement.classList.add('side-icons');
            productContainerSideSpanIconElement.classList.add('fas');
            productContainerSideSpanIconElement.classList.add('fa-search');
            productBottomElement.classList.add('bottom');
            productBottomLinkElement.innerHTML = products[i]['itemName'];
            productBottomPriceElement.classList.add('price');
            productBottomPriceSpanElement.innerHTML = '₩'
                + products[i]['itemPrice'].toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");

            kinds = products[i]['itemKinds'] - 1;

            if (check[kinds].checked){
                    itemSectionLayoutColLayout.append(productElement);
            }
        }
    };

    const fallback = () => {
        alert('데이터가 없습니다');
    };
    Ajax.request('POST', 'apis/product/productList', callback, fallback);
    return false;
};


// checkbox
function value_check() {
    let check_count = document.getElementsByName("cateKinds").length;

    for (let i=0; i < check_count; i++) {
        if (document.getElementsByName("cateKinds")[i].checked === true) {
            console.log(document.getElementsByName("cateKinds")[i].value);
        }
    }
}


