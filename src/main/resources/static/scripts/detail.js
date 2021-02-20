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
            let detailRightFormElement = document.createElement('form');
            let detailRightFormContainerElement = document.createElement('div');
            let detailRightFormContainerDivElement = document.createElement('div');
            let detailRightFormContainerDivSelectElement = document.createElement('select');
            let detailRightFormContainerDivSelectOptionColorElement = document.createElement('option');
            let detailRightFormContainerDivSelectOptionColor1Element = document.createElement('option');
            let detailRightFormContainerDivSelectOptionColor2Element = document.createElement('option');
            let detailRightFormContainerDivSelectOptionColor3Element = document.createElement('option');
            let detailRightFormContainerDivSelectOptionColor4Element = document.createElement('option');
            let detailRightFormContainerDivSpanElement = document.createElement('span');
            let detailRightFormColorDivSpanIconElement = document.createElement('i');
            let detailRightFormCartElement = document.createElement('div');
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
            detailRightElement.append(detailRightFormElement);

            detailRightFormElement.append(detailRightFormContainerElement);
            detailRightFormContainerElement.append(detailRightFormContainerDivElement);
            detailRightFormContainerDivElement.append(detailRightFormContainerDivSelectElement);
            detailRightFormContainerDivSelectElement.append(detailRightFormContainerDivSelectOptionColorElement);
            detailRightFormContainerDivSelectElement.append(detailRightFormContainerDivSelectOptionColor1Element);
            detailRightFormContainerDivSelectElement.append(detailRightFormContainerDivSelectOptionColor2Element);
            detailRightFormContainerDivSelectElement.append(detailRightFormContainerDivSelectOptionColor3Element);
            detailRightFormContainerDivSelectElement.append(detailRightFormContainerDivSelectOptionColor4Element);
            detailRightFormContainerDivElement.append(detailRightFormContainerDivSpanElement);
            detailRightFormContainerDivSpanElement.append(detailRightFormColorDivSpanIconElement);
            detailRightFormElement.append(detailRightFormCartElement);
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
                for (let i = 0; i < relateProducts.length; i++) {
                    let detailLeftThumbsThumbnailElement = document.createElement('div');
                    let detailLeftThumbsThumbnailImgElement = document.createElement('img');
                    detailLeftThumbsElement.append(detailLeftThumbsThumbnailElement);
                    detailLeftThumbsThumbnailElement.append(detailLeftThumbsThumbnailImgElement);
                    detailLeftThumbsThumbnailElement.classList.add('thumbnail');
                    detailLeftThumbsThumbnailImgElement.src = '/apis/product/imageList?index=' + relateProducts[i]['itemIndex'];
                }
            };
            const relateFallback = () => {
                for (let i = 0; i < 4; i++) {
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
            detailRightFormElement.classList.add('form-detail');
            detailRightFormElement.action = '/apis/product/addDetail';
            detailRightFormElement.id = 'formDetailId';
            detailRightFormElement.name = 'formDetailName';
            detailRightFormContainerElement.classList.add('color-container');
            detailRightFormContainerDivSelectElement.name = 'color';
            detailRightFormContainerDivSelectOptionColorElement.value = 'SELECT COLOR';
            detailRightFormContainerDivSelectOptionColorElement.selected = true;
            detailRightFormContainerDivSelectOptionColorElement.disabled = true;
            detailRightFormContainerDivSelectOptionColorElement.innerHTML = 'SELECT COLOR';
            detailRightFormContainerDivSelectOptionColor1Element.value = 'white';
            detailRightFormContainerDivSelectOptionColor1Element.innerHTML = 'WHITE';
            detailRightFormContainerDivSelectOptionColor2Element.value = 'black';
            detailRightFormContainerDivSelectOptionColor2Element.innerHTML = 'BLACK';
            detailRightFormContainerDivSelectOptionColor3Element.value = 'brown';
            detailRightFormContainerDivSelectOptionColor3Element.innerHTML = 'BROWN';
            detailRightFormContainerDivSelectOptionColor4Element.value = 'blue';
            detailRightFormContainerDivSelectOptionColor4Element.innerHTML = 'BLUE';
            detailRightFormColorDivSpanIconElement.classList.add('fas');
            detailRightFormColorDivSpanIconElement.classList.add('fa-chevron-down');

            detailRightFormCartElement.classList.add('form-detail');
            detailRightFormCartElement.classList.add('size');
            detailRightFormCartElement.append(detailRightFormCartCountElement);
            detailRightFormCartCountElement.type = "text";
            detailRightFormCartCountElement.name = 'size';
            detailRightFormCartCountElement.placeholder = "SIZE";
            detailRightFormCartElement.append(detailRightFormCartLinkElement);
            detailRightFormCartLinkElement.href = 'javascript:formDetailName.submit();';
            detailRightFormCartLinkElement.classList.add('addCart');
            detailRightFormCartLinkElement.onclick = () => {
                // ajax add 추가
                const addCallback = (addResponse) => {
                    let jsonAdd = JSON.parse(addResponse);
                    if (jsonAdd['result'] === 'success') {
                        Dialog.show('장바구니', '장바구니에 담았습니다.', ['확인'], [() => {
                            Dialog.hide();
                        }]);
                        addDetailClick(index);
                    } else {
                        addFallback();
                    }
                };
                const addFallback = () => {
                    Dialog.show('장바구니', '장바구니에 담지 못했습니다.', ['확인'], [() => {
                        Dialog.hide();
                    }]);
                };
                let addFormData = new FormData(formDetailId);
                addFormData.append("productIndex", product['itemIndex']);
                addFormData.append("imageIndex", index);
                Ajax.request('POST', 'apis/product/addDetail', addCallback, addFallback, addFormData);
                return false;
            };
            detailRightFormCartLinkElement.innerHTML = 'Add To Cart';

            detailRightElement.append(detailRightH3Element);
            detailRightH3Element.innerHTML = 'Product Detail';
            detailRightElement.append(detailRightPElement);
            detailRightPElement.innerHTML = product['itemDetail'];

            detailSectionContainer.append(detailLeftElement);
            detailSectionContainer.append(detailRightElement);

        };

        const fallback = () => {
            Dialog.show('상세정보', '정보가 없습니다.', ['확인'], [() => {
                Dialog.hide();
            }]);
        };

        let formData = new FormData();
        formData.append("index", index);
        Ajax.request('POST', 'apis/product/detail', callback, fallback, formData);
        return false;
    }

}