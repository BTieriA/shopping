<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="styles/login.css">
</head>
<body>
<div id="js-dialog" class="body-item dialog">
    <div class="dialog-item cover"></div>
    <div class="dialog-item window">
        <div class="window-item title"></div>
        <div class="window-item content"></div>
        <div class="window-item buttons"></div>
    </div>
</div>
<section>
    <div class="container">
        <div class="user signinBx">
            <div class="imgBx"><img src="images/adv1.png"></div>
            <div class="formBx">
                <form id="loginForm" method="POST" action="/apis/user/login">
                    <h2>Sign In</h2>
                    <input type="text" name="name" placeholder="Username" value="admin">
                    <input type="password" name="password" placeholder="Password" value="1234">
                    <input type="submit" name="" value="Login">
                    <p class="signup">Don't have an account ? <a href="#" onclick="toggleForm();">Sign Up.</a></p>
                </form>
            </div>
        </div>
        <div class="user signupBx">
            <div class="formBx">
                <form id="signUpForm" method="POST" action="/apis/user/signup">
                    <h2>Create an account</h2>
                    <input type="text" name="newName" placeholder="Username">
                    <input type="email" name="newEmail" placeholder="Email Address">
                    <div class="radioBx">
                        <label for="customer">CUSTOMER</label><input type="radio" name="newLevel" id="customer"
                                                                     checked="checked" value="0">
                        <label for="admin">ADMIN</label><input type="radio" name="newLevel" id="admin" value="1">
                    </div>
                    <input type="password" name="newPassword" id="newPassword" placeholder="Create Password">
                    <input type="password" name="confirm" id="confirm" onchange="confirmPw()" placeholder="Confirm Password">
                    <label id="PwResult"></label>
                    <input type="submit" name="" value="Sign Up">
                    <p class="signup">Already have an account ? <a href="#" onclick="toggleForm();">Sign in.</a></p>
                </form>
            </div>
            <div class="imgBx"><img src="images/adv2.jpg"></div>
        </div>
    </div>
</section>
<script src="scripts/login.js"></script>
<script src="scripts/ajax.js"></script>
<script src="scripts/dialog.js"></script>
</body>
</html>
