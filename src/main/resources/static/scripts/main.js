// Responsive - Navigation -> Button
const openNav = document.querySelector(".open-btn");
const closeNav = document.querySelector(".close-btn");
const menu = document.querySelector(".nav-list");
const menuLeft = menu.getBoundingClientRect().left;
// getBoundingClientRect() : 위치값
openNav.addEventListener("click", () => {
    if (menuLeft < 0) {
        menu.classList.add("show");
    }
});
closeNav.addEventListener("click", () => {
    if (menuLeft < 0) {
        menu.classList.remove("show");
    }
});

// Ajax - Logout
let logout = window.document.querySelector('.logout');

logout.addEventListener('click', () => {
    const callback = (response) => {
        let json = JSON.parse(response);
        if (json['result'] === 'logout') {
            alert('로그아웃 되었습니다');
            window.location.href = "/";
        } else {
            fallback();
        }
    };

    const fallback = () => {
        alert('로그인 상태입니다');
    };

    Ajax.request('POST', 'apis/user/logout', callback, fallback);
    return false;
});

// admin mode
let adminClick = () => {
    window.location.href = "admin";
};

let noAdminClick = () => {
    alert('관리자 권한이 없습니다');
    homeClick();
};

AOS.init();