class AdminAnswer {
    static adminAnswerLayout = (index) => {
        let adminAnswerData = (index) => {
            let qna = window.document.querySelector('.js-qna');
            let qnaMain = qna.querySelector('.list');
            qnaMain.innerHTML = '';

            const callback = (response) => {
                let json = JSON.parse(response);
                let result = json['result'];
                let detail = json['detail'];
                if (result === 'success'){
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
                        qnaListClick();
                    };
                    qnaMainMenuListElement.innerHTML = "Answer";
                    qnaMainMenuListElement.onclick = () => {

                    };

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
                    let qnaMainTableHeadTrDateElement = document.createElement('th');
                    let qnaMainTableHeadTrContentElement = document.createElement('tr');
                    let qnaMainTableHeadTrNoContentElement = document.createElement('td');
                    let qnaMainTableHeadTrTitleContentElement = document.createElement('td');
                    let qnaMainTableHeadTrDateContentElement = document.createElement('td');

                    qnaMainTableHeadTrNoElement.innerHTML = 'NO';
                    qnaMainTableHeadTrTitleElement.innerHTML = 'TITLE';
                    qnaMainTableHeadTrDateElement.innerHTML = 'DATE';

                    qnaMainTableHeadTrNoContentElement.innerHTML = detail['qnaIndex'];
                    qnaMainTableHeadTrTitleContentElement.innerHTML = detail['qnaTitle'];
                    qnaMainTableHeadTrDateContentElement.innerHTML = detail['qnaDate'];

                    qnaMain.append(qnaMainTableElement);
                    qnaMainTableElement.append(qnaMainTableHeadElement);
                    qnaMainTableHeadElement.append(qnaMainTableHeadTrElement);
                    qnaMainTableHeadTrElement.append(qnaMainTableHeadTrNoElement);
                    qnaMainTableHeadTrElement.append(qnaMainTableHeadTrTitleElement);
                    qnaMainTableHeadTrElement.append(qnaMainTableHeadTrDateElement);
                    qnaMainTableHeadElement.append(qnaMainTableHeadTrContentElement);
                    qnaMainTableHeadTrContentElement.append(qnaMainTableHeadTrNoContentElement);
                    qnaMainTableHeadTrContentElement.append(qnaMainTableHeadTrTitleContentElement);
                    qnaMainTableHeadTrContentElement.append(qnaMainTableHeadTrDateContentElement);

                    let qnaMainTableBodyElement = document.createElement('tbody');
                    qnaMainTableElement.append(qnaMainTableBodyElement);

                    // 상세내용
                    let qnaMainTableBodyTrElement = document.createElement('tr');
                    let qnaMainTableBodyTrThElement = document.createElement('th');
                    let qnaMainTableBodyTrContentElement = document.createElement('tr');
                    let qnaMainTableBodyTrContentTdElement = document.createElement('td');

                    qnaMainTableBodyTrThElement.colSpan = 3;
                    qnaMainTableBodyTrThElement.innerHTML = 'CONTENT';
                    qnaMainTableBodyTrThElement.classList.add('content');
                    qnaMainTableBodyTrContentTdElement.colSpan = 3;
                    qnaMainTableBodyTrContentTdElement.innerHTML = detail['qnaContent'];
                    qnaMainTableBodyTrContentTdElement.classList.add('content-text');
                    qnaMainTableBodyElement.append(qnaMainTableBodyTrElement);
                    qnaMainTableBodyTrElement.append(qnaMainTableBodyTrThElement);
                    qnaMainTableBodyElement.append(qnaMainTableBodyTrContentElement);
                    qnaMainTableBodyTrContentElement.append(qnaMainTableBodyTrContentTdElement);

                    // Answer
                    let qnaMainAnswerElement = document.createElement('div');
                    let qnaMainFormElement = document.createElement('form');
                    let qnaMainFormLabelElement = document.createElement('label');
                    let qnaMainFormLabelTextareaElement = document.createElement('textarea');
                    let qnaMainFormButtonElement = document.createElement('div');
                    let qnaMainFormButtonSubmitElement = document.createElement('input');

                    qnaMainAnswerElement.classList.add('title');
                    qnaMainAnswerElement.innerHTML = 'Answer';
                    qnaMainFormElement.classList.add('ansForm');
                    qnaMainFormElement.id = 'ansForm';
                    qnaMainFormLabelElement.classList.add('ansLabel');
                    qnaMainFormLabelTextareaElement.name = "content";
                    qnaMainFormButtonElement.classList.add('button');
                    qnaMainFormButtonSubmitElement.type = 'submit';
                    qnaMainFormButtonSubmitElement.value = 'SUBMIT';

                    qnaMain.append(qnaMainAnswerElement);
                    qnaMain.append(qnaMainFormElement);
                    qnaMainFormElement.append(qnaMainFormLabelElement);
                    qnaMainFormLabelElement.append(qnaMainFormLabelTextareaElement);
                    qnaMainFormElement.append(qnaMainFormButtonElement);
                    qnaMainFormButtonElement.append(qnaMainFormButtonSubmitElement);

                    let ansForm = document.querySelector('#ansForm');
                    ansForm.onsubmit = () => {
                        const ansCallback = (ansResponse) => {
                            let ansJson = JSON.parse(ansResponse);
                            let ansResult = ansJson['result'];
                            if (ansResult === 'success') {
                                Dialog.show('ANSWER', '답변을 하였습니다.', ['확인'], [() => {
                                    Dialog.hide();
                                }]);
                            } else {
                                ansFallback();
                            }
                        };
                        const ansFallback = () => {
                            Dialog.show('ANSWER', '답변이 등록 되지 못했습니다.', ['확인'], [() => {
                                Dialog.hide();
                            }]);
                        };
                        let ansFormData = new FormData(ansForm);
                        ansFormData.append("index", index);
                        Ajax.request('POST', 'apis/customer/addAnswer', ansCallback, ansFallback, ansFormData);
                        return false;
                    }

                } else {
                    fallback();
                }

            };
            const fallback = () => {
                Dialog.show('ANSWER', '답변을 달지 못했습니다.', ['확인'], [() => {
                    Dialog.hide();
                }]);
            };

            let formData = new FormData();
            formData.append("index", index);
            Ajax.request('POST', '/apis/customer/qna', callback, fallback, formData);
            return false;
        };
        adminAnswerData(index);
    }
}