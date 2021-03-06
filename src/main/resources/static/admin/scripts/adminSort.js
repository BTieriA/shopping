class AdminSortList {
    static sortListData = (kinds) => {
        let items = window.document.querySelector('.js-items');
        let itemSection = items.querySelector('.products');
        let itemSectionLayout = itemSection.querySelector('.products-layout');
        let itemSectionLayoutCol = itemSectionLayout.querySelector('.col-3-of-4');
        let itemSectionLayoutColLayout = itemSectionLayoutCol.querySelector('.product-layout');
        let check = document.getElementsByName("cateKinds");
        let cartCount = window.document.querySelector('.count');

        let getLists = (page) => {
            cartCount.innerHTML = '0';

            const callback = (response) => {
                items.classList.add('visible');

                let json = JSON.parse(response);
                let products = json['products'];
                if (products === 'NoData'){
                    fallback();
                    return;
                }
                itemSectionLayoutColLayout.innerHTML = '';
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
                    productContainerCartIconElement.classList.add('fa-trash');
                    productContainerCartElement.onclick = () => {
                        Dialog.show('DELETE', '삭제하겠습니까?', ['네', '아니요'], [
                            () => {
                                Dialog.hide();
                                const delCallback = (delResponse) => {
                                    let delJson = JSON.parse(delResponse);
                                    let delResult = delJson['result'];
                                    if (delResult === 'success') {
                                        window.location.reload();
                                    } else if (delResult === 'invalid') {
                                        Dialog.show('DELETE', '삭제 권한이 없습니다.', ['확인'], [() => {
                                            Dialog.hide();
                                        }]);
                                    } else {
                                        delFallback();
                                    }
                                };
                                const delFallback = () => {
                                    Dialog.show('DELETE', '삭제되지 않았습니다.', ['확인'], [() => {
                                        Dialog.hide();
                                    }]);
                                };
                                formData = new FormData();
                                formData.append('index', products[i]['itemIndex']);
                                Ajax.request('POST', 'apis/product/delete', delCallback, delFallback, formData);
                                return false;
                            },
                            () => {
                                Dialog.show('DELETE', '삭제되지 않았습니다.', ['확인'], [() => {
                                    Dialog.hide();
                                }]);
                            }
                        ]);
                    };
                    productContainerSideElement.classList.add('side-icons');
                    productContainerSideSpanElement.classList.add('gotoDetail');
                    productContainerSideSpanElement.onclick = () => {
                        adminDetailClick(products[i]['itemIndex']);
                        return false;
                    };
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

                let itemSectionLayoutColPageElement = document.querySelector('.pagination');
                itemSectionLayoutColPageElement.innerHTML = '';
                let itemSectionLayoutColPageElementSpanFirstElement = document.createElement('span');
                let itemSectionLayoutColPageElementSpanPreElement = document.createElement('span');
                let itemSectionLayoutColPageElementSpanNextElement = document.createElement('span');
                let itemSectionLayoutColPageElementSpanLastElement = document.createElement('span');

                itemSectionLayoutColPageElement.classList.add('pagination');
                itemSectionLayoutColPageElementSpanFirstElement.classList.add('first');
                itemSectionLayoutColPageElementSpanPreElement.classList.add('icon');
                itemSectionLayoutColPageElementSpanNextElement.classList.add('icon');
                itemSectionLayoutColPageElementSpanLastElement.classList.add('last');

                let startPage = parseInt(json['start_page']);
                let endPage = parseInt(json['end_page']);
                let reqPage = parseInt(json['page']);
                let maxPage = parseInt(json['max_page']);

                if (reqPage > 1) {
                    itemSectionLayoutColPageElementSpanFirstElement.addEventListener('click', () => {
                        getLists(1);
                    });
                    itemSectionLayoutColPageElementSpanPreElement.addEventListener('click', () => {
                        getLists(reqPage - 1);
                    });
                    itemSectionLayoutColPageElementSpanFirstElement.innerHTML = '<< first';
                    itemSectionLayoutColPageElementSpanPreElement.innerHTML = '<';
                    itemSectionLayoutColPageElement.append(itemSectionLayoutColPageElementSpanFirstElement);
                    itemSectionLayoutColPageElement.append(itemSectionLayoutColPageElementSpanPreElement);

                }

                for (let i = startPage; i <= endPage; i++) {
                    let itemSectionLayoutColPageElementSpan1Element = document.createElement('span');
                    itemSectionLayoutColPageElementSpan1Element.innerHTML = `${i}`;
                    if (i === reqPage) {
                        itemSectionLayoutColPageElementSpan1Element.classList.add('selected');
                    } else {
                        itemSectionLayoutColPageElementSpan1Element.addEventListener('click', () => {
                            getLists(i);
                        })
                    }
                    itemSectionLayoutColPageElement.append(itemSectionLayoutColPageElementSpan1Element);
                }
                if (reqPage < maxPage) {
                    itemSectionLayoutColPageElementSpanNextElement.addEventListener('click', () => {
                        getLists(reqPage + 1);
                    });
                    itemSectionLayoutColPageElementSpanLastElement.addEventListener('click', () => {
                        getLists(maxPage);
                    });
                    itemSectionLayoutColPageElementSpanNextElement.innerHTML = '>';
                    itemSectionLayoutColPageElementSpanLastElement.innerHTML = 'last >>';
                    itemSectionLayoutColPageElement.append(itemSectionLayoutColPageElementSpanNextElement);
                    itemSectionLayoutColPageElement.append(itemSectionLayoutColPageElementSpanLastElement);
                }

            };

            const fallback = () => {
                Dialog.show('목록', '데이터가 없습니다', ['확인'], [() => {
                    Dialog.hide();
                }]);
            };

            let formData = new FormData();
            formData.append('page', page);
            Ajax.request('POST', 'apis/product/productList', callback, fallback, formData);
        };
        getLists(1);


        let sortFormElement = itemSectionLayoutCol.querySelector('#sortForm');
        let getSortLists = (page) => {
            const callback = (response) => {
                let json = JSON.parse(response);
                let products = json['products'];
                if (products === 'NoData'){
                    fallback();
                    return;
                }
                itemSectionLayoutColLayout.innerHTML = '';
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
                    productContainerCartIconElement.classList.add('fa-trash');
                    productContainerSideElement.classList.add('side-icons');
                    productContainerSideSpanElement.classList.add('gotoDetail');
                    productContainerSideSpanElement.onclick = () => {
                        // detailClick(products[i]['itemIndex']);
                        AdminUpdate.updateItem(products[i]['itemIndex']);
                        return false;
                    };
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

                let itemSectionLayoutColPageElement = document.querySelector('.pagination');
                itemSectionLayoutColPageElement.innerHTML = '';
                let itemSectionLayoutColPageElementSpanFirstElement = document.createElement('span');
                let itemSectionLayoutColPageElementSpanPreElement = document.createElement('span');
                let itemSectionLayoutColPageElementSpanNextElement = document.createElement('span');
                let itemSectionLayoutColPageElementSpanLastElement = document.createElement('span');

                itemSectionLayoutColPageElement.classList.add('pagination');
                itemSectionLayoutColPageElementSpanFirstElement.classList.add('first');
                itemSectionLayoutColPageElementSpanPreElement.classList.add('icon');
                itemSectionLayoutColPageElementSpanNextElement.classList.add('icon');
                itemSectionLayoutColPageElementSpanLastElement.classList.add('last');

                let startPage = parseInt(json['start_page']);
                let endPage = parseInt(json['end_page']);
                let reqPage = parseInt(json['page']);
                let maxPage = parseInt(json['max_page']);

                if (reqPage > 1) {
                    itemSectionLayoutColPageElementSpanFirstElement.addEventListener('click', () => {
                        getSortLists(1);
                    });
                    itemSectionLayoutColPageElementSpanPreElement.addEventListener('click', () => {
                        getSortLists(reqPage - 1);
                    });
                    itemSectionLayoutColPageElementSpanFirstElement.innerHTML = '<< first';
                    itemSectionLayoutColPageElementSpanPreElement.innerHTML = '<';
                    itemSectionLayoutColPageElement.append(itemSectionLayoutColPageElementSpanFirstElement);
                    itemSectionLayoutColPageElement.append(itemSectionLayoutColPageElementSpanPreElement);

                }

                for (let i = startPage; i <= endPage; i++) {
                    let itemSectionLayoutColPageElementSpan1Element = document.createElement('span');
                    itemSectionLayoutColPageElementSpan1Element.innerHTML = `${i}`;
                    if (i === reqPage) {
                        itemSectionLayoutColPageElementSpan1Element.classList.add('selected');
                    } else {
                        itemSectionLayoutColPageElementSpan1Element.addEventListener('click', () => {
                            getSortLists(i);
                        })
                    }
                    itemSectionLayoutColPageElement.append(itemSectionLayoutColPageElementSpan1Element);
                }
                if (reqPage < maxPage) {
                    itemSectionLayoutColPageElementSpanNextElement.addEventListener('click', () => {
                        getSortLists(reqPage + 1);
                    });
                    itemSectionLayoutColPageElementSpanLastElement.addEventListener('click', () => {
                        getSortLists(maxPage);
                    });
                    itemSectionLayoutColPageElementSpanNextElement.innerHTML = '>';
                    itemSectionLayoutColPageElementSpanLastElement.innerHTML = 'last >>';
                    itemSectionLayoutColPageElement.append(itemSectionLayoutColPageElementSpanNextElement);
                    itemSectionLayoutColPageElement.append(itemSectionLayoutColPageElementSpanLastElement);
                }
            };

            const fallback = () => {
                Dialog.show('목록', '데이터가 없습니다', ['확인'], [() => {
                    Dialog.hide();
                }]);
            };

            let formData = new FormData(sortFormElement);
            formData.append('page', page);
            Ajax.request('POST', 'apis/product/sortList', callback, fallback, formData);
            return false;
        };

        sortFormElement.onsubmit = () => {
            getSortLists();
            return false;
        }
    }
}

