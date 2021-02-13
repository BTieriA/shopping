// var
let admin = window.document.querySelector('.js-admin');
let addBox = window.document.querySelector('.js-addition');

// Image Upload - preview
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


