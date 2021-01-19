// var
let admin = window.document.querySelector('.js-admin');
let home = window.document.querySelector('.home');
let addBox = window.document.querySelector('.js-addition');


// Go to Main
home.addEventListener('click', () =>{
    admin.classList.add('visible');
    window.location.href = "main";
});


// Ajax - insert
let insertClick = () => {
    let showItem = window.document.querySelector('.js-items');
    admin.classList.remove('visible');
    addBox.classList.add('visible');
    showItem.classList.remove('visible');
}


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


