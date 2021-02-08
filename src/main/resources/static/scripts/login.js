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
            Dialog.show('회원가입', '회원가입 하신 것을 축하합니다.', ['확인'], [() => {
                Dialog.hide();
            }]);
        } else {
            fallback(response);
        }
    };
    const fallback = (response) => {
        let json = JSON.parse(response);
        if(json['result'] === 'name_duplication'){
            Dialog.show('회원가입', '같은 이름이 존재합니다.', ['확인'], [() => {
                Dialog.hide();
            }]);
        } else if (json['result'] === 'email_duplication'){
            Dialog.show('회원가입', '같은 이메일이 존재합니다.', ['확인'], [() => {
                Dialog.hide();
            }]);
        } else if (json['result'] === 'unauthorized'){
            Dialog.show('회원가입', '관리자 모드로 가입하실 수 없습니다.', ['확인'], [() => {
                Dialog.hide();
            }]);
        } else {
            Dialog.show('회원가입', '회원가입에 실패하셨습니다. <br> 다시 회원가입 해주세요', ['확인'], [() => {
                Dialog.hide();
            }]);
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
            Dialog.show('로그인', '가구 쇼핑몰에 오신 것을 환영합니다.', ['확인'], [() => {
                Dialog.hide();
                window.location.href = "main"
            }]);
        } else {
            fallback();
        }
    };

    const fallback = () => {
        Dialog.show('로그인', '로그인에 실패하셨습니다. <br> 아이디랑 비밀번호르 확인해주세요', ['확인'], [() => {
            Dialog.hide();
        }]);
    };
    let formData = new FormData(loginForm);
    Ajax.request('POST','/apis/user/login', callback, fallback, formData);
    return false;
};


