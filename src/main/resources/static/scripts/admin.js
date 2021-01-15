// var
let admin = window.document.querySelector('.js-admin');
let home = window.document.querySelector('.home');
let addBox = window.document.querySelector('.js-addition');
let addition = window.document.querySelector('.insertProduct')

// Go to Main
home.addEventListener('click', () =>{
    admin.classList.remove('hidden');
    window.location.href = "main";
});


// Ajax - insert
addition.addEventListener('click',()=>{
    admin.classList.add('hidden');
    addBox.classList.add('visible');
    let callback = (responseText) => {
        addBox.innerHTML =responseText;
    };
    let fallback = () => {
        alert('페이지를 찾을 수 없습니다');
    };
    Ajax.request('GET', 'parts/addProduct.html', callback, fallback);
});


// Image Upload
let uploadClick = () => {
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
        }
    });
};


