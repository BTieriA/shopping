// 화면 전환
function toggleForm(){
    let container = document.querySelector('.container');
    container.classList.toggle('active')
}

// 패스워드 확인
let confirmPw = () => {
    let pw = document.getElementById('newPassword');
    let confirm = document.getElementById('confirm');
    let result = document.getElementById('PwResult');
    if (pw.value != confirm.value){
        result.innerHTML = 'Incorrect Password';
        result.style.color = 'red';
        return false;
    } else{
        result.innerHTML = 'Correct Password';
        result.style.color = 'blue';
        return true;
    }
}

// Ajax - Sign up
let signUpForm = window.document.body.querySelector("#signUpForm");
signUpForm.onsubmit = () => {
    const callback = (response) => {
        let json = JSON.parse(response);
        if(json['result'] === 'success'){
            alert('회원으로 등록되었습니다');
        } else {
            fallback(response);
        }
    };
    const fallback = (response) => {
        let json = JSON.parse(response);
        if(json['result'] === 'name_duplication'){
            alert('같은 이름이 존재합니다.');
        } else if (json['result'] === 'email_duplication'){
            alert('같은 이름이 존재합니다.');
        } else if (json['result'] === 'unauthorized'){
            alert('관리자 가입이 허락되지 않았습니다');
        } else {
            alert('회원등록에 실패하였습니다.');
        }
    };

    let formData = new FormData(signUpForm);
    Ajax.request('POST','/apis/user/signup', callback, fallback, formData);
    return false;
}




// Ajax - Login
let loginForm = window.document.body.querySelector("#loginForm");
loginForm.onsubmit = () => {
    const callback = (response) => {
        let json = JSON.parse(response);
        if(json['result'] === 'success'){
            alert('로그인이 되었습니다');
            window.location.href = "main"
        } else {
            fallback();
        }
    };

    const fallback = () => {
        alert('로그인에 실패하였습니다');
    };
    let formData = new FormData(loginForm);
    Ajax.request('POST','/apis/user/login', callback, fallback, formData);
    return false;
}


