let addForm = window.document.querySelector('#addForm');
let items = window.document.querySelector('.js-items');
addForm.onsubmit = () => {
    const callback = (response) => {
        let json = JSON.parse(response);
        if(json['product_input'] === 'success'){
            alert('가구가 등록되었습니다');
            addBox.classList.remove('visible');
            items.classList.add('visible');
        } else {
            fallback();
        }
    }
    const fallback = () => {
        alert('등록에 실패하였습니다');
    }

    let formData = new FormData(addForm);
    formData.append("imageFile", imgUpload.files[0]);
    Ajax.request('POST','apis/product/addProduct', callback, fallback, formData);
    return false;
};
