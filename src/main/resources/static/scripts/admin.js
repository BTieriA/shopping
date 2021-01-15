// var
let admin = window.document.querySelector('.js-admin');
let home = window.document.querySelector('.home');
let addBox = window.document.querySelector('.js-addition');
let addition = window.document.querySelector('.insertProduct');

// Go to Main
home.addEventListener('click', () =>{
    admin.classList.remove('hidden');
    window.location.href = "main";
});


// Ajax - insert
addition.addEventListener('click',()=>{
    admin.classList.add('hidden');
    addBox.classList.add('visible');
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
            document.querySelector('.uploadName').value = elem.files[0].name;
        }
    });
};


