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
                        AdminAnsList.adminAnsListData();
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

                    const ansCallback = (ansResponse) => {
                        let ansJson = JSON.parse(ansResponse);
                        let ansResult = ansJson['result'];
                        let ansAnswer = ansJson['answer'];
                        if (ansResult === 'success') {
                            // 답변 제목
                            let qnaMainAnsTitleElement = document.createElement('div');
                            qnaMainAnsTitleElement.classList.add('title');
                            qnaMainAnsTitleElement.innerHTML='Answer';
                            qnaMain.append(qnaMainAnsTitleElement);

                            // 답변 테이블 생성
                            let qnaMainAnsTableElement = document.createElement('table');
                            qnaMainAnsTableElement.classList.add('list-item');
                            qnaMainAnsTableElement.classList.add('qna-list');
                            let qnaMainAnsTableHeadElement = document.createElement('thead');
                            let qnaMainAnsTableHeadTrElement = document.createElement('tr');
                            let qnaMainAnsTableHeadTrNoElement = document.createElement('th');
                            let qnaMainAnsTableHeadTrTitleElement = document.createElement('th');
                            let qnaMainAnsTableHeadTrDateElement = document.createElement('th');
                            let qnaMainAnsTableHeadTrContentElement = document.createElement('tr');
                            let qnaMainAnsTableHeadTrNoContentElement = document.createElement('td');
                            let qnaMainAnsTableHeadTrTitleContentElement = document.createElement('td');
                            let qnaMainAnsTableHeadTrDateContentElement = document.createElement('td');

                            qnaMainAnsTableHeadTrNoElement.innerHTML = 'NO';
                            qnaMainAnsTableHeadTrTitleElement.innerHTML = 'ADMIN NAME';
                            qnaMainAnsTableHeadTrDateElement.innerHTML = 'DATE';

                            qnaMainAnsTableHeadTrNoContentElement.innerHTML = ansAnswer['customerIndex'];
                            qnaMainAnsTableHeadTrTitleContentElement.innerHTML = ansAnswer['userName'];
                            qnaMainAnsTableHeadTrDateContentElement.innerHTML = ansAnswer['ansDate'];

                            qnaMain.append(qnaMainAnsTableElement);
                            qnaMainAnsTableElement.append(qnaMainAnsTableHeadElement);
                            qnaMainAnsTableHeadElement.append(qnaMainAnsTableHeadTrElement);
                            qnaMainAnsTableHeadTrElement.append(qnaMainAnsTableHeadTrNoElement);
                            qnaMainAnsTableHeadTrElement.append(qnaMainAnsTableHeadTrTitleElement);
                            qnaMainAnsTableHeadTrElement.append(qnaMainAnsTableHeadTrDateElement);
                            qnaMainAnsTableHeadElement.append(qnaMainAnsTableHeadTrContentElement);
                            qnaMainAnsTableHeadTrContentElement.append(qnaMainAnsTableHeadTrNoContentElement);
                            qnaMainAnsTableHeadTrContentElement.append(qnaMainAnsTableHeadTrTitleContentElement);
                            qnaMainAnsTableHeadTrContentElement.append(qnaMainAnsTableHeadTrDateContentElement);

                            let qnaMainAnsTableBodyElement = document.createElement('tbody');
                            qnaMainAnsTableElement.append(qnaMainAnsTableBodyElement);

                            // 상세내용
                            let qnaMainAnsTableBodyTrElement = document.createElement('tr');
                            let qnaMainAnsTableBodyTrThElement = document.createElement('th');
                            let qnaMainAnsTableBodyTrContentElement = document.createElement('tr');
                            let qnaMainAnsTableBodyTrContentTdElement = document.createElement('td');

                            qnaMainAnsTableBodyTrThElement.colSpan = 3;
                            qnaMainAnsTableBodyTrThElement.innerHTML = 'ANSWER';
                            qnaMainAnsTableBodyTrThElement.classList.add('content');
                            qnaMainAnsTableBodyTrContentTdElement.colSpan = 3;
                            qnaMainAnsTableBodyTrContentTdElement.innerHTML = ansAnswer['ansContent'];
                            qnaMainAnsTableBodyTrContentTdElement.classList.add('content-text');
                            qnaMainAnsTableBodyElement.append(qnaMainAnsTableBodyTrElement);
                            qnaMainAnsTableBodyTrElement.append(qnaMainAnsTableBodyTrThElement);
                            qnaMainAnsTableBodyElement.append(qnaMainAnsTableBodyTrContentElement);
                            qnaMainAnsTableBodyTrContentElement.append(qnaMainAnsTableBodyTrContentTdElement);

                            // 버튼
                            let qnaMainFormElement = document.createElement('form');
                            let qnaMainFormUpdateBoxElement = document.createElement('div');
                            let qnaMainFormDeleteBoxElement = document.createElement('div');
                            let qnaMainButtonBoxUpdateElement = document.createElement('input');
                            let qnaMainButtonBoxDeleteElement = document.createElement('input');

                            qnaMainFormUpdateBoxElement.classList.add('updateBox');
                            qnaMainFormDeleteBoxElement.classList.add('deleteBox');
                            qnaMainButtonBoxUpdateElement.classList.add('updateBtn');
                            qnaMainButtonBoxDeleteElement.classList.add('deleteBtn');

                            qnaMainFormElement.id = 'ansBtnForm';

                            qnaMainButtonBoxUpdateElement.type = 'submit';
                            qnaMainButtonBoxDeleteElement.type = 'submit';
                            qnaMainButtonBoxUpdateElement.value = 'MODIFY';
                            qnaMainButtonBoxDeleteElement.value = 'DELETE';

                            qnaMain.append(qnaMainFormElement);
                            qnaMainFormElement.append(qnaMainFormUpdateBoxElement);
                            qnaMainFormElement.append(qnaMainFormDeleteBoxElement);
                            qnaMainFormUpdateBoxElement.append(qnaMainButtonBoxUpdateElement);
                            qnaMainFormDeleteBoxElement.append(qnaMainButtonBoxDeleteElement);

                            qnaMainFormElement.method = 'POST';

                            let btnFrom = window.document.querySelector('#ansBtnForm');
                            qnaMainButtonBoxUpdateElement.onclick = () => {
                                qnaMainFormElement.action = 'apis/customer/answer';
                                btnFrom.onsubmit = () => {
                                    const updateCallback = (updateResponse) => {
                                        let updateJson = JSON.parse(updateResponse);
                                        let updateResult = updateJson['result'];
                                        let updateData = updateJson['answer'];
                                        qnaMainFormElement.innerHTML = '';
                                        if ( updateResult === 'success') {
                                            // 테이블 제목
                                            let qnaMainUpdateTitleElement = document.createElement('div');
                                            qnaMainUpdateTitleElement.classList.add('title');
                                            qnaMainUpdateTitleElement.innerHTML='Modify Answer';
                                            qnaMain.append(qnaMainUpdateTitleElement);

                                            // 테이블 생성
                                            let qnaMainUpdateFormElement = document.createElement('form');
                                            let qnaMainUpdateFormTableElement = document.createElement('table');
                                            qnaMainUpdateFormTableElement.classList.add('list-item');
                                            qnaMainUpdateFormTableElement.classList.add('qna-list');
                                            let qnaMainUpdateFormTableHeadElement = document.createElement('thead');
                                            let qnaMainUpdateFormTableHeadTrElement = document.createElement('tr');
                                            let qnaMainUpdateFormTableHeadTrNoElement = document.createElement('th');
                                            let qnaMainUpdateFormTableHeadTrTitleElement = document.createElement('th');
                                            let qnaMainUpdateFormTableHeadTrDateElement = document.createElement('th');
                                            let qnaMainUpdateFormTableHeadTrContentElement = document.createElement('tr');
                                            let qnaMainUpdateFormTableHeadTrNoContentElement = document.createElement('td');
                                            let qnaMainUpdateFormTableHeadTrNameContentElement = document.createElement('td');
                                            let qnaMainUpdateFormTableHeadTrDateContentElement = document.createElement('td');

                                            qnaMainUpdateFormElement.classList.add('modForm');
                                            qnaMainUpdateFormElement.id = 'modForm';
                                            qnaMainUpdateFormElement.action = 'apis/customer/updateAns';
                                            qnaMainUpdateFormTableHeadTrNoElement.innerHTML = 'NO';
                                            qnaMainUpdateFormTableHeadTrTitleElement.innerHTML = 'USER NAME';
                                            qnaMainUpdateFormTableHeadTrDateElement.innerHTML = 'DATE';

                                            qnaMainUpdateFormTableHeadTrNoContentElement.innerHTML = updateData['customerIndex'];
                                            qnaMainUpdateFormTableHeadTrNameContentElement.innerHTML = updateData['userName'];
                                            qnaMainUpdateFormTableHeadTrDateContentElement.innerHTML = updateData['ansDate'];

                                            qnaMain.append(qnaMainUpdateFormElement);
                                            qnaMainUpdateFormElement.append(qnaMainUpdateFormTableElement);
                                            qnaMainUpdateFormTableElement.append(qnaMainUpdateFormTableHeadElement);
                                            qnaMainUpdateFormTableHeadElement.append(qnaMainUpdateFormTableHeadTrElement);
                                            qnaMainUpdateFormTableHeadTrElement.append(qnaMainUpdateFormTableHeadTrNoElement);
                                            qnaMainUpdateFormTableHeadTrElement.append(qnaMainUpdateFormTableHeadTrTitleElement);
                                            qnaMainUpdateFormTableHeadTrElement.append(qnaMainUpdateFormTableHeadTrDateElement);
                                            qnaMainUpdateFormTableHeadElement.append(qnaMainUpdateFormTableHeadTrContentElement);
                                            qnaMainUpdateFormTableHeadTrContentElement.append(qnaMainUpdateFormTableHeadTrNoContentElement);
                                            qnaMainUpdateFormTableHeadTrContentElement.append(qnaMainUpdateFormTableHeadTrNameContentElement);
                                            qnaMainUpdateFormTableHeadTrContentElement.append(qnaMainUpdateFormTableHeadTrDateContentElement);

                                            let qnaMainUpdateFormTableBodyElement = document.createElement('tbody');
                                            qnaMainUpdateFormTableElement.append(qnaMainUpdateFormTableBodyElement);

                                            // 상세내용
                                            let qnaMainUpdateFormTableBodyTrElement = document.createElement('tr');
                                            let qnaMainUpdateFormTableBodyTrThElement = document.createElement('th');
                                            let qnaMainUpdateFormTableBodyTrContentElement = document.createElement('tr');
                                            let qnaMainUpdateFormTableBodyTrContentTdElement = document.createElement('td');
                                            let qnaMainUpdateFormTableBodyTrContentTdInputElement = document.createElement('textarea');

                                            qnaMainUpdateFormTableBodyTrThElement.colSpan = 3;
                                            qnaMainUpdateFormTableBodyTrThElement.innerHTML = 'CONTENT';
                                            qnaMainUpdateFormTableBodyTrThElement.classList.add('content');
                                            qnaMainUpdateFormTableBodyTrContentTdElement.colSpan = 3;
                                            qnaMainUpdateFormTableBodyTrContentTdElement.classList.add('content-text');
                                            qnaMainUpdateFormTableBodyTrContentTdInputElement.name = 'content';
                                            qnaMainUpdateFormTableBodyTrContentTdInputElement.placeholder = '내용을' +
                                                ' 입력해주세요';

                                            qnaMainUpdateFormTableBodyElement.append(qnaMainUpdateFormTableBodyTrElement);
                                            qnaMainUpdateFormTableBodyTrElement.append(qnaMainUpdateFormTableBodyTrThElement);
                                            qnaMainUpdateFormTableBodyElement.append(qnaMainUpdateFormTableBodyTrContentElement);
                                            qnaMainUpdateFormTableBodyTrContentElement.append(qnaMainUpdateFormTableBodyTrContentTdElement);
                                            qnaMainUpdateFormTableBodyTrContentTdElement.append(qnaMainUpdateFormTableBodyTrContentTdInputElement);

                                            // 버튼
                                            let qnaMainUpdateFormButtonBoxElement = document.createElement('div');
                                            let qnaMainUpdateFormButtonBoxSubmitElement = document.createElement('input');

                                            qnaMainUpdateFormButtonBoxElement.classList.add('modBtnBox');
                                            qnaMainUpdateFormButtonBoxSubmitElement.classList.add('modSubmit');
                                            qnaMainUpdateFormButtonBoxSubmitElement.type = 'submit';
                                            qnaMainUpdateFormButtonBoxSubmitElement.value = 'Modify';

                                            qnaMainUpdateFormElement.append(qnaMainUpdateFormButtonBoxElement);
                                            qnaMainUpdateFormButtonBoxElement.append(qnaMainUpdateFormButtonBoxSubmitElement);

                                            let modForm = window.document.querySelector('#modForm');
                                            qnaMainUpdateFormButtonBoxSubmitElement.onclick = () => {
                                                modForm.onsubmit = () => {
                                                    const modCallback = (modResponse) => {
                                                        let modJson = JSON.parse(modResponse);
                                                        if (modJson['result'] === 'success') {
                                                            Dialog.show('MODIFY', '수정하였습니다.', ['확인'], [() => {
                                                                Dialog.hide();
                                                                adminAnswerData(index);
                                                            }]);
                                                        }
                                                    };
                                                    const modFallback = () => {
                                                        Dialog.show('MODIFY', '수정하지 못하였습니다.', ['확인'], [() => {
                                                            Dialog.hide();
                                                        }]);
                                                    };
                                                    let modFormData = new FormData(modForm);
                                                    modFormData.append("index", updateData['customerIndex']);
                                                    Ajax.request('POST', 'apis/customer/updateAns', modCallback, modFallback, modFormData);
                                                    return false;
                                                }
                                            }
                                        } else {
                                            updateFallback();
                                        }

                                    };
                                    const updateFallback = () => {
                                        Dialog.show('MODIFY', '수정 페이지로 이동하지 못했습니다.', ['확인'], [() => {
                                            Dialog.hide();
                                        }]);
                                    };
                                    let updateFormData = new FormData(btnFrom);
                                    updateFormData.append("index", index);
                                    Ajax.request('POST', 'apis/customer/answer', updateCallback, updateFallback, updateFormData);
                                    return false;
                                };
                            };
                            qnaMainButtonBoxDeleteElement.onclick = () => {
                                qnaMainFormElement.action = 'apis/customer/deleteAns';
                                btnFrom.onsubmit = () => {
                                    const delCallback = (delResponse) => {
                                        let delJson = JSON.parse(delResponse);
                                        let delResult = delJson['result'];
                                        if (delResult === 'success') {
                                            Dialog.show('DELETE', '삭제하였습니다.', ['확인'], [() => {
                                                Dialog.hide();
                                                adminAnswerData(index);
                                            }]);
                                        } else{
                                            delFallback();
                                        }
                                    };
                                    const delFallback = () => {
                                        Dialog.show('DELETE', '삭제하지 못하였습니다.', ['확인'], [() => {
                                            Dialog.hide();
                                        }]);
                                    };
                                    let delFormData = new FormData(btnFrom);
                                    delFormData.append("index", index);
                                    Ajax.request('POST', 'apis/customer/deleteAns', delCallback, delFallback, delFormData);
                                    return false;
                                };
                            };
                        } else {
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
                                            qnaListClick();
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

                        }
                    };
                    const ansFallback = () => {
                        Dialog.show('ANSWER', '답변이 아직 없습니다.', ['확인'], [() => {
                            Dialog.hide();
                        }]);
                    };
                    let ansFormData = new FormData();
                    ansFormData.append("index", index);
                    Ajax.request('POST', '/apis/customer/answer', ansCallback, ansFallback, ansFormData);
                    return false;
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