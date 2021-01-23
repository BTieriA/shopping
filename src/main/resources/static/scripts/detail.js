class Detail {
    static detailData = (index) => {
        let detail = window.document.querySelector('.js-detail');
        let detailSection = detail.querySelector('.product-details');
        let detailSectionContainer = detailSection.querySelector('.details');

        detailSectionContainer.innerHTML = '';

        const callback = (response) => {
            let json = JSON.parse(response);
            let product = json['product'];

            detail.classList.add('visible');

            let detailLeftElement = document.createElement('div');
            let detailLeftMainElement = document.createElement('div');
            let detailLeftMainImgElement = document.createElement('img');
            let detailLeftThumbsElement = document.createElement('div');
            let detailRightElement = document.createElement('div');
            let detailRightSpanElement = document.createElement('span');
            let detailRightH1Element = document.createElement('h1');
            let detailRightPriceElement = document.createElement('div');
            let detailRightFormColorElement = document.createElement('form');
            let detailRightFormColorDivElement = document.createElement('div');
            let detailRightFormColorDivSelectElement = document.createElement('select');
            let detailRightFormColorDivSelectOptionColorElement = document.createElement('option');
            let detailRightFormColorDivSelectOptionColor1Element = document.createElement('option');
            let detailRightFormColorDivSelectOptionColor2Element = document.createElement('option');
            let detailRightFormColorDivSelectOptionColor3Element = document.createElement('option');
            let detailRightFormColorDivSelectOptionColor4Element = document.createElement('option');
            let detailRightFormColorDivSpanElement = document.createElement('span');
            let detailRightFormColorDivSpanIconElement = document.createElement('i');
            let detailRightFormCartElement = document.createElement('form');
            let detailRightFormCartCountElement = document.createElement('input');
            let detailRightFormCartLinkElement = document.createElement('a');
            let detailRightH3Element = document.createElement('h3');
            let detailRightPElement = document.createElement('p');

            detailLeftElement.append(detailLeftMainElement);
            detailLeftMainElement.append(detailLeftMainImgElement);
            detailLeftElement.append(detailLeftThumbsElement);

            detailRightElement.append(detailRightSpanElement);
            detailRightElement.append(detailRightH1Element);
            detailRightElement.append(detailRightH1Element);
            detailRightElement.append(detailRightPriceElement);
            detailRightElement.append(detailRightFormColorElement);
            detailRightFormColorElement.append(detailRightFormColorDivElement);
            detailRightFormColorDivElement.append(detailRightFormColorDivSelectElement);
            detailRightFormColorDivSelectElement.append(detailRightFormColorDivSelectOptionColorElement);
            detailRightFormColorDivSelectElement.append(detailRightFormColorDivSelectOptionColor1Element);
            detailRightFormColorDivSelectElement.append(detailRightFormColorDivSelectOptionColor2Element);
            detailRightFormColorDivSelectElement.append(detailRightFormColorDivSelectOptionColor3Element);
            detailRightFormColorDivSelectElement.append(detailRightFormColorDivSelectOptionColor4Element);
            detailRightFormColorDivElement.append(detailRightFormColorDivSpanElement);
            detailRightFormColorDivSpanElement.append(detailRightFormColorDivSpanIconElement);
            detailRightElement.append(detailRightFormCartElement);
            detailRightFormCartElement.append(detailRightFormCartCountElement);
            detailRightFormCartElement.append(detailRightFormCartLinkElement);
            detailRightElement.append(detailRightH3Element);
            detailRightElement.append(detailRightPElement);
            
            detailLeftElement.classList.add('left');
            detailLeftMainElement.classList.add('detail-main');
            detailLeftMainImgElement.src = '/apis/product/imageList?index=' + index;
            detailLeftThumbsElement.classList.add('thumbnails');

            // Related Product
            const relateCallback = (relateResponse) => {
                let jsonRelate = JSON.parse(relateResponse);
                let relateProducts = jsonRelate['relateProduct'];
                for (let i = 0; i < relateProducts.length; i++ ) {
                    let detailLeftThumbsThumbnailElement = document.createElement('div');
                    let detailLeftThumbsThumbnailImgElement = document.createElement('img');
                    detailLeftThumbsElement.append(detailLeftThumbsThumbnailElement);
                    detailLeftThumbsThumbnailElement.append(detailLeftThumbsThumbnailImgElement);
                    detailLeftThumbsThumbnailElement.classList.add('thumbnail');
                    detailLeftThumbsThumbnailImgElement.src = '/apis/product/imageList?index=' + relateProducts[i]['itemIndex'];
                }
            };
            const relateFallback = () => {
                for (let i = 0; i < 4; i++ ) {
                    let detailLeftThumbsThumbnailElement = document.createElement('div');
                    let detailLeftThumbsThumbnailImgElement = document.createElement('img');
                    detailLeftThumbsElement.append(detailLeftThumbsThumbnailElement);
                    detailLeftThumbsThumbnailElement.append(detailLeftThumbsThumbnailImgElement);
                    detailLeftThumbsThumbnailElement.classList.add('thumbnail');
                    detailLeftThumbsThumbnailImgElement.src = 'images/default.jpg';
                }
            };
            let relateFormData = new FormData();
            relateFormData.append("kinds", product['itemKinds']);
            Ajax.request('POST', 'apis/product/relate', relateCallback, relateFallback, relateFormData);


            detailRightElement.classList.add('right');
            detailRightSpanElement.innerHTML = product['itemBrand'];
            detailRightH1Element.innerHTML = product['itemName'];
            detailRightPriceElement.classList.add('price');
            detailRightPriceElement.innerHTML = '₩'
                + product['itemPrice'].toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");

            // form - color
            detailRightFormColorDivSelectOptionColorElement.value = 'SELECT COLOR';
            detailRightFormColorDivSelectOptionColorElement.selected = true;
            detailRightFormColorDivSelectOptionColorElement.disabled = true;
            detailRightFormColorDivSelectOptionColorElement.innerHTML = 'SELECT COLOR';
            detailRightFormColorDivSelectOptionColor1Element.value = 'white';
            detailRightFormColorDivSelectOptionColor1Element.innerHTML = 'WHITE';
            detailRightFormColorDivSelectOptionColor2Element.value = 'black';
            detailRightFormColorDivSelectOptionColor2Element.innerHTML = 'BLACK';
            detailRightFormColorDivSelectOptionColor3Element.value = 'brown';
            detailRightFormColorDivSelectOptionColor3Element.innerHTML = 'BROWN';
            detailRightFormColorDivSelectOptionColor4Element.value = 'blue';
            detailRightFormColorDivSelectOptionColor4Element.innerHTML = 'BLUE';
            detailRightFormColorDivSpanIconElement.classList.add('fas');
            detailRightFormColorDivSpanIconElement.classList.add('fa-chevron-down');

            // form - cart
            detailRightFormCartElement.classList.add('form');
            detailRightFormCartElement.append(detailRightFormCartCountElement);
            detailRightFormCartCountElement.type = "text";
            detailRightFormCartCountElement.placeholder = "SIZE";
            detailRightFormCartElement.append(detailRightFormCartLinkElement);
            detailRightFormCartLinkElement.href= '#';
            detailRightFormCartLinkElement.classList.add('addCart');
            detailRightFormCartLinkElement.innerHTML = 'Add To Cart';

            detailRightElement.append(detailRightH3Element);
            detailRightH3Element.innerHTML = 'Product Detail';
            detailRightElement.append(detailRightPElement);
            detailRightPElement.innerHTML = product['itemDetail'];

            detailSectionContainer.append(detailLeftElement);
            detailSectionContainer.append(detailRightElement);
        };

        const fallback = () => {
            alert('데이터가 없습니다');
        };

        let formData = new FormData();
        formData.append("index", index);
        Ajax.request('POST', 'apis/product/detail', callback, fallback, formData);
        return false;
    }
}
