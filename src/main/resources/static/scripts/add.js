let upload = window.document.getElementById("imgUpload");
let preview = window.document.querySelector(".preview");


upload.addEventListener('change', function(e){
    let elem = e.target;
    preview.src = URL.createObjectURL(elem.files[0]);
    preview.onload = function() {
        URL.revokeObjectURL(preview.src);
    };

    if(window.FileReader) {
        let filename = elem.files[0].name;
        document.querySelector('.uploadName').value = filename;
    };
});

// Ajax - Image Upload
let uploadForm = window.document.querySelector('.product-insert');
uploadForm.onsubmit = () => {
    const callback = (response) => {
        let json = JSON.parse(response);
        if(json['result'] === 'success'){
            alert('사진이 등록 되었습니다');
            let addition = window.document.querySelector('.js-addition')
            addition.classList.add('hidden');
            let additionCallback = (responseText) => {
                let items = window.document.querySelector('.js-items');
                items.innerHTML = responseText;
            };
            let additionFallback = () => {
                alert('Not Found Page');
            };
            Ajax.request('GET', 'parts/items.html', additionCallback, additionFallback);
        } else {
            fallback();
        }
    };

    const fallback = (response) => {
        let json = JSON.parse(response);
        if(json['result'] === 'not_allowed'){
            alert('관리자로 로그인하셔야 등록이 가능합니다');
        } else {
            alert('사진 등록이 되지 않았습니다');
        }
    };

    let formData = new FormData(uploadForm);
    formData.append("imageFile", imgUpload.files[0]);
    Ajax.request('POST','/apis/product/addProduct', callback, fallback, formData);
    return false;
}
