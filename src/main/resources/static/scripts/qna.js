class Qna {
    static qnaList = () => {
        let qnaTable = (page) => {
            let qna = window.document.querySelector('.js-qna');
            let qnaMain = qna.querySelector('.list');
            qnaMain.innerHTML = '';

            const callback = (response) => {
                let json = JSON.parse(response);
                let qnaList = json['qnaList'];
                if (qnaList === 'no_data'){
                    fallback();
                } else if (qnaList === 'no_authorized') {
                    Dialog.show('QNA', '로그인 해주세요', ['확인'], [() => {
                        Dialog.hide();
                    }]);
                } else {
                    // 테이블 목록
                    let qnaMainMenuElement =document.createElement('ul');
                    qnaMainMenuElement.classList.add('list-item');
                    qnaMainMenuElement.classList.add('menu');
                    let qnaMainMenuTitleElement =document.createElement('li');
                    qnaMainMenuTitleElement.classList.add('menu-item');
                    qnaMainMenuTitleElement.classList.add('qna-title');
                    let qnaMainMenuListElement =document.createElement('li');
                    qnaMainMenuListElement.classList.add('menu-item');
                    qnaMainMenuListElement.classList.add('qna-list');
                    qnaMainMenuTitleElement.innerHTML = 'Question';
                    qnaMainMenuTitleElement.onclick = () => {
                        qnaClick();
                    };
                    qnaMainMenuListElement.innerHTML = "LIST";
                    qnaMain.append(qnaMainMenuElement);
                    qnaMainMenuElement.append(qnaMainMenuTitleElement);
                    qnaMainMenuElement.append(qnaMainMenuListElement);

                    // 테이블 생성
                    let qnaMainTableElement = document.createElement('table');
                    qnaMainTableElement.classList.add('list-item');
                    qnaMainTableElement.classList.add('qna-list');
                    let qnaMainTableHeadElement = document.createElement('thead');
                    let qnaMainTableHeadTrElement = document.createElement('tr');
                    let qnaMainTableHeadTrNoElement = document.createElement('th');
                    let qnaMainTableHeadTrTitleElement = document.createElement('th');
                    let qnaMainTableHeadTrContentElement = document.createElement('th');
                    let qnaMainTableHeadTrDateElement = document.createElement('th');

                    qnaMainTableHeadTrNoElement.innerHTML = 'NO';
                    qnaMainTableHeadTrTitleElement.innerHTML = 'TITLE';
                    qnaMainTableHeadTrContentElement.innerHTML = 'CONTENT';
                    qnaMainTableHeadTrDateElement.innerHTML = 'DATE';

                    qnaMain.append(qnaMainTableElement);
                    qnaMainTableElement.append(qnaMainTableHeadElement);
                    qnaMainTableHeadElement.append(qnaMainTableHeadTrElement);
                    qnaMainTableHeadTrElement.append(qnaMainTableHeadTrNoElement);
                    qnaMainTableHeadTrElement.append(qnaMainTableHeadTrTitleElement);
                    qnaMainTableHeadTrElement.append(qnaMainTableHeadTrContentElement);
                    qnaMainTableHeadTrElement.append(qnaMainTableHeadTrDateElement);

                    let qnaMainTableBodyElement = document.createElement('tbody');
                    qnaMainTableElement.append(qnaMainTableBodyElement);

                    for (let i=0; i < qnaList.length; i++) {
                        // 리스트
                        let qnaMainTableBodyTrElement = document.createElement('tr');
                        let qnaMainTableBodyTrIndexElement = document.createElement('td');
                        let qnaMainTableBodyTrTitleElement = document.createElement('td');
                        let qnaMainTableBodyTrContentElement = document.createElement('td');
                        let qnaMainTableBodyTrDateElement = document.createElement('td');

                        qnaMainTableBodyTrIndexElement.innerHTML = qnaList[i]['qnaIndex'];
                        qnaMainTableBodyTrTitleElement.innerHTML = qnaList[i]['qnaTitle'];
                        qnaMainTableBodyTrContentElement.innerHTML = qnaList[i]['qnaContent'];
                        qnaMainTableBodyTrDateElement.innerHTML = qnaList[i]['qnaDate'];

                        qnaMainTableBodyElement.append(qnaMainTableBodyTrElement);
                        qnaMainTableBodyTrElement.append(qnaMainTableBodyTrIndexElement);
                        qnaMainTableBodyTrElement.append(qnaMainTableBodyTrTitleElement);
                        qnaMainTableBodyTrElement.append(qnaMainTableBodyTrContentElement);
                        qnaMainTableBodyTrElement.append(qnaMainTableBodyTrDateElement);
                    }

                    // PAGE
                    let qnaMainTableFootElement = document.createElement('tfoot');
                    let qnaMainTableFootTrElement = document.createElement('tr');
                    let qnaMainTableFootColElement = document.createElement('td');
                    qnaMainTableFootColElement.colSpan = 4;
                    let qnaMainTableFootColPageElement = document.createElement('div');
                    qnaMainTableFootColPageElement.classList.add('pagination');
                    qnaMainTableFootColPageElement.innerHTML = '';
                    let qnaMainTableFootColPageFirstElement = document.createElement('span');
                    let qnaMainTableFootColPagePreElement = document.createElement('span');
                    let qnaMainTableFootColPageNextElement = document.createElement('span');
                    let qnaMainTableFootColPageLastElement = document.createElement('span');
                    qnaMainTableFootColPageFirstElement.classList.add('first');
                    qnaMainTableFootColPagePreElement.classList.add('icon');
                    qnaMainTableFootColPageNextElement.classList.add('icon');
                    qnaMainTableFootColPageLastElement.classList.add('last');

                    qnaMainTableElement.append(qnaMainTableFootElement);
                    qnaMainTableFootElement.append(qnaMainTableFootTrElement);
                    qnaMainTableFootTrElement.append(qnaMainTableFootColElement);
                    qnaMainTableFootColElement.append(qnaMainTableFootColPageElement);

                    let startPage = parseInt(json['start_page']);
                    let endPage = parseInt(json['end_page']);
                    let reqPage = parseInt(json['page']);
                    let maxPage = parseInt(json['max_page']);

                    if (reqPage > 1) {
                        qnaMainTableFootColPageFirstElement.addEventListener('click', () => {
                            qnaTable(1);
                        });
                        qnaMainTableFootColPagePreElement.addEventListener('click', () => {
                            qnaTable(reqPage - 1);
                        });
                        qnaMainTableFootColPageFirstElement.innerHTML = '<< first';
                        qnaMainTableFootColPagePreElement.innerHTML = '<';
                        qnaMainTableFootColPageElement.append(qnaMainTableFootColPageFirstElement);
                        qnaMainTableFootColPageElement.append(qnaMainTableFootColPagePreElement);

                    }

                    for (let i = startPage; i <= endPage; i++) {
                        let qnaMainTableFootColPageElementSpan1Element = document.createElement('span');
                        qnaMainTableFootColPageElementSpan1Element.innerHTML = `${i}`;
                        if (i === reqPage) {
                            qnaMainTableFootColPageElementSpan1Element.classList.add('selected');
                        } else {
                            qnaMainTableFootColPageElementSpan1Element.addEventListener('click', () => {
                                qnaTable(i);
                            })
                        }
                        qnaMainTableFootColPageElement.append(qnaMainTableFootColPageElementSpan1Element);
                    }
                    if (reqPage < maxPage) {
                        qnaMainTableFootColPageNextElement.addEventListener('click', () => {
                            qnaTable(reqPage + 1);
                        });
                        qnaMainTableFootColPageLastElement.addEventListener('click', () => {
                            qnaTable(maxPage);
                        });
                        qnaMainTableFootColPageNextElement.innerHTML = '>';
                        qnaMainTableFootColPageLastElement.innerHTML = 'last >>';
                        qnaMainTableFootColPageElement.append(qnaMainTableFootColPageNextElement);
                        qnaMainTableFootColPageElement.append(qnaMainTableFootColPageLastElement);
                    }

                }

            };
            const fallback = () => {

            };
            let formData = new FormData();
            formData.append('page', page);
            Ajax.request('POST', '/apis/customer/qnaList', callback, fallback, formData);
        }
        qnaTable(1);
    }
}