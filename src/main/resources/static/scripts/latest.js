class Latest {
    static latestItem = () => {
        let getLatestItem = () => {
            let content = window.document.querySelector('.js-content');
            let contentProducts = content.querySelector('.products');

            contentProducts.innerHTML = '';
            const callback = (response) => {
                let json = JSON.parse(response);
                let product = json['latestProduct'];
                if (product === 'NoData'){
                    fallback();
                    return;
                }
                let titleElement = document.createElement('div');
                let titleH2Element = document.createElement('h2');
                let titleSpanElement = document.createElement('span');
                let layoutElement = document.createElement('div');
                titleElement.classList.add('title');
                contentProducts.append(titleElement);
                titleElement.append(titleH2Element);
                titleH2Element.innerHTML = 'New Products';
                titleElement.append(titleSpanElement);
                titleSpanElement.innerHTML = "Select from the premium product and save plenty money";
                contentProducts.append(layoutElement);
                layoutElement.classList.add('product-layout');
                for (let i = 0; i < product.length; i++){
                    let layoutProductElement = document.createElement('div');
                    let layoutProductImgContainerElement = document.createElement('div');
                    let layoutProductImgContainerImgElement = document.createElement('img');
                    let layoutProductImgContainerSideElement = document.createElement('ul');
                    let layoutProductImgContainerSideSearchElement = document.createElement('span');
                    let layoutProductBottomElement = document.createElement('div');
                    let layoutProductBottomNameElement = document.createElement('a');
                    let layoutProductBottomPriceElement = document.createElement('div');
                    let layoutProductBottomPriceSpanElement = document.createElement('span');

                    layoutElement.append(layoutProductElement);
                    layoutProductElement.classList.add('product');
                    layoutProductElement.append(layoutProductImgContainerElement);
                    layoutProductImgContainerElement.classList.add('img-container');
                    layoutProductImgContainerElement.append(layoutProductImgContainerImgElement);
                    layoutProductImgContainerImgElement.src = "/apis/product/imageList?index=" + product[i]['itemIndex'];
                    layoutProductImgContainerElement.append(layoutProductImgContainerSideElement);
                    layoutProductImgContainerSideElement.classList.add('side-icons');
                    layoutProductImgContainerSideElement.append(layoutProductImgContainerSideSearchElement);
                    layoutProductImgContainerSideElement.onclick = () => {
                        detailClick(product[i]['itemIndex']);
                        return false;
                    };
                    layoutProductImgContainerSideSearchElement.classList.add('fas');
                    layoutProductImgContainerSideSearchElement.classList.add('fa-search');
                    layoutProductElement.append(layoutProductBottomElement);
                    layoutProductBottomElement.classList.add('bottom');
                    layoutProductBottomElement.append(layoutProductBottomNameElement);
                    layoutProductBottomNameElement.innerHTML = product[i]['itemName'];
                    layoutProductBottomElement.append(layoutProductBottomPriceElement);
                    layoutProductBottomPriceElement.classList.add('price');
                    layoutProductBottomPriceElement.append(layoutProductBottomPriceSpanElement);
                    layoutProductBottomPriceSpanElement.innerHTML = '₩'
                        + product[i]['itemPrice'].toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                }
            };
            const fallback = () => {
                Dialog.show('Product', '새로운 제품이 없습니다.', ['확인'], [() => {
                    Dialog.hide();
                }]);
            };
            Ajax.request('POST', 'apis/product/latest', callback, fallback);
        };
        getLatestItem();
    }
}