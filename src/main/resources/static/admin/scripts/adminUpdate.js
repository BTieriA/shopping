class AdminUpdate {
    static updateItem = (index) => {
        let updateData = (index) => {
            let update = window.document.querySelector('.js-addition');
            update.innerHTML = '';
            let updateSection = document.createElement('div');
            let updateSectionForm = document.createElement('form');
            updateSection.classList.add('section');
            updateSection.classList.add('product-insert');
            update.append(updateSection);
            updateSection.append(updateSectionForm);

            updateSectionForm.classList.add('details');
            updateSectionForm.classList.add('container');
            updateSectionForm.id = 'updateForm';
            updateSectionForm.method = 'POST';
            updateSection.append(updateSectionForm);
            const callback = (response) => {
                let json = JSON.parse(response);
                let product = json['product'];

                let leftElement = document.createElement('div');
                let leftFileBoxElement = document.createElement('div');
                let leftFileBoxImgElement = document.createElement('img');

                let rightElement = document.createElement('div');
                let rightBrandBoxElement = document.createElement('div');
                let rightBrandElement = document.createElement('select');
                let rightBrandOptionElement = document.createElement('option');
                let rightBrandHanssemElement = document.createElement('option');
                let rightBrandIloomElement = document.createElement('option');
                let rightBrandWellzElement = document.createElement('option');
                let rightBrandYoungElement = document.createElement('option');
                let rightSpanElement = document.createElement('span');
                let rightSpanIconElement = document.createElement('i');
                let rightNameElement = document.createElement('div');
                let rightNameInputElement = document.createElement('input');
                let rightPriceElement = document.createElement('div');
                let rightPriceInputElement = document.createElement('input');
                let rightKindsBoxElement = document.createElement('div');
                let rightKindsBoxSelectElement = document.createElement('select');
                let rightKindsBoxSelectOptionElement = document.createElement('option');
                let rightKindsBoxSelectOptionBedElement = document.createElement('option');
                let rightKindsBoxSelectOptionSofaElement = document.createElement('option');
                let rightKindsBoxSelectOptionCabinetElement = document.createElement('option');
                let rightKindsBoxSelectOptionTableElement = document.createElement('option');
                let rightKindsBoxSelectOptionChairElement = document.createElement('option');
                let rightKindsBoxSelectOptionSinkElement = document.createElement('option');
                let rightKindsBoxSelectOptionEtcElement = document.createElement('option');
                let rightKindsBoxSpanElement = document.createElement('span');
                let rightKindsBoxSpanIconElement = document.createElement('i');
                let rightDetailElement = document.createElement('h3');
                let rightTextareaElement = document.createElement('textarea');
                let rightSubmitBoxElement = document.createElement('div');
                let rightSubmitBoxUpdateElement = document.createElement('input');
                let rightSubmitBoxDeleteElement = document.createElement('input');

                leftElement.classList.add('left');
                leftFileBoxElement.classList.add('fileBox');
                leftFileBoxImgElement.classList.add('preview');
                leftFileBoxImgElement.src = '/apis/product/imageList?index=' + product['itemIndex'];

                updateSectionForm.append(leftElement);
                leftElement.append(leftFileBoxElement);
                leftFileBoxElement.append(leftFileBoxImgElement);

                rightElement.classList.add('right');
                rightBrandBoxElement.classList.add('brandBox');
                rightBrandElement.id = 'brand';
                rightBrandElement.name = 'brand';
                rightBrandOptionElement.value = 'Select Brands';
                rightBrandOptionElement.disabled;
                rightBrandOptionElement.innerHTML = 'SELECT BRANDS';
                rightBrandHanssemElement.value = 'HANSSEM';
                rightBrandHanssemElement.innerHTML = 'HANSSEM';
                rightBrandIloomElement.value = 'iloom';
                rightBrandIloomElement.innerHTML = 'iloom';
                rightBrandWellzElement.value = 'WELLZ';
                rightBrandWellzElement.innerHTML = 'WELLZ';
                rightBrandYoungElement.value = 'youngDongGagu';
                rightBrandYoungElement.innerHTML = 'youngDongGagu';
                updateSectionForm.append(rightElement);
                rightElement.append(rightBrandBoxElement);

                rightBrandBoxElement.append(rightBrandElement);
                rightBrandElement.append(rightBrandOptionElement);
                rightBrandElement.append(rightBrandHanssemElement);
                rightBrandElement.append(rightBrandIloomElement);
                rightBrandElement.append(rightBrandWellzElement);
                rightBrandElement.append(rightBrandYoungElement);

                rightSpanIconElement.classList.add('fas');
                rightSpanIconElement.classList.add('fa-chevron-down');
                rightBrandBoxElement.append(rightSpanElement);
                rightSpanElement.append(rightSpanIconElement);

                rightNameElement.classList.add('name');
                rightNameInputElement.type = 'text';
                rightNameInputElement.id = 'name';
                rightNameInputElement.name = 'name';
                rightNameInputElement.value = product['itemName'];
                rightElement.append(rightNameElement);
                rightNameElement.append(rightNameInputElement);

                rightPriceElement.classList.add('price');
                rightPriceInputElement.type = 'text';
                rightPriceInputElement.id = 'price';
                rightPriceInputElement.name = 'price';
                rightPriceInputElement.value = product['itemPrice'];
                rightElement.append(rightPriceElement);
                rightPriceElement.append(rightPriceInputElement);

                rightKindsBoxElement.classList.add('kindsBox');
                rightKindsBoxSelectElement.name = 'kinds';
                rightKindsBoxSelectOptionElement.value = 'Select Kinds';
                rightKindsBoxSelectOptionElement.disabled;
                rightKindsBoxSelectOptionElement.innerHTML = 'SELECT KINDS';
                rightKindsBoxSelectOptionBedElement.value = '1';
                rightKindsBoxSelectOptionBedElement.innerHTML = 'BED';
                rightKindsBoxSelectOptionSofaElement.value = '2';
                rightKindsBoxSelectOptionSofaElement.innerHTML = 'SOFA';
                rightKindsBoxSelectOptionCabinetElement.value = '3';
                rightKindsBoxSelectOptionCabinetElement.innerHTML = 'CABINET';
                rightKindsBoxSelectOptionTableElement.value = '4';
                rightKindsBoxSelectOptionTableElement.innerHTML = 'TABLE';
                rightKindsBoxSelectOptionChairElement.value = '5';
                rightKindsBoxSelectOptionChairElement.innerHTML = 'CHAIR';
                rightKindsBoxSelectOptionSinkElement.value = '6';
                rightKindsBoxSelectOptionSinkElement.innerHTML = 'SINK';
                rightKindsBoxSelectOptionEtcElement.value = '7';
                rightKindsBoxSelectOptionEtcElement.innerHTML = 'ETC';
                rightElement.append(rightKindsBoxElement);
                rightKindsBoxElement.append(rightKindsBoxSelectElement);
                rightKindsBoxSelectElement.append(rightKindsBoxSelectOptionElement);
                rightKindsBoxSelectElement.append(rightKindsBoxSelectOptionBedElement);
                rightKindsBoxSelectElement.append(rightKindsBoxSelectOptionSofaElement);
                rightKindsBoxSelectElement.append(rightKindsBoxSelectOptionCabinetElement);
                rightKindsBoxSelectElement.append(rightKindsBoxSelectOptionTableElement);
                rightKindsBoxSelectElement.append(rightKindsBoxSelectOptionChairElement);
                rightKindsBoxSelectElement.append(rightKindsBoxSelectOptionSinkElement);
                rightKindsBoxSelectElement.append(rightKindsBoxSelectOptionEtcElement);

                rightKindsBoxSpanIconElement.classList.add('fas');
                rightKindsBoxSpanIconElement.classList.add('fa-chevron-down');
                rightElement.append(rightKindsBoxSpanElement);
                rightKindsBoxSpanElement.append(rightKindsBoxSpanIconElement);

                rightDetailElement.innerHTML = 'Product Detail';
                rightTextareaElement.cols = 60;
                rightTextareaElement.rows = 10;
                rightTextareaElement.name = 'detail';
                rightSubmitBoxElement.classList.add('submitBox');
                rightSubmitBoxUpdateElement.type = 'submit';
                rightSubmitBoxUpdateElement.classList.add('updateSubmit');
                rightSubmitBoxUpdateElement.formAction = '/apis/product/update';
                rightSubmitBoxUpdateElement.value = 'Update Product';
                rightElement.append(rightDetailElement);
                rightElement.append(rightTextareaElement);
                rightElement.append(rightSubmitBoxElement);
                rightSubmitBoxElement.append(rightSubmitBoxUpdateElement);
                let updateFormId = window.document.querySelector('#updateForm');
                updateFormId.onsubmit = () => {
                    const updateCallback = (updateResponse) => {
                        let jsonUpdate = JSON.parse(updateResponse);
                        let result = jsonUpdate['result'];
                        if (result === 'success') {
                            Dialog.show('UPDATE', '업데이트 되었습니다', ['확인'], [() => {
                                Dialog.hide();
                            }]);
                        } else {
                            updateFallback();
                        }
                    };
                    const updateFallback = () => {
                        Dialog.show('UPDATE', '업데이트 되지 않았습니다.', ['확인'], [() => {
                            Dialog.hide();
                        }]);
                    };
                    let updateFormData = new FormData(updateFormId);
                    updateFormData.append('index', product['itemIndex']);
                    Ajax.request('POST', '/apis/product/update', updateCallback, updateFallback, updateFormData);
                    return false;
                };

            };

            const fallback = () => {
                Dialog.show('UPDATE', '업데이트 되지 않았습니다.', ['확인'], [() => {
                    Dialog.hide();
                }]);
            };

            let formData = new FormData();
            formData.append("index", index);
            Ajax.request('POST', '/apis/product/detail', callback, fallback, formData);
        };
        updateData(index);
    }
}