<%@ page import="com.tieria.shopping.common.UserVo" %>
<%@ page import="com.tieria.shopping.common.Converter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    UserVo userVo = Converter.getUserVo(request);
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Favicon -->
    <link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">
    <!-- Glidejs -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/Glide.js/3.4.1/css/glide.core.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/Glide.js/3.4.1/css/glide.theme.css">
    <%-- AOS --%>
    <link href="https://unpkg.com/aos@2.3.1/dist/aos.css" rel="stylesheet">
    <!-- Custom StyleSheet -->
    <link rel="stylesheet" href="styles/common.css">
    <title>SM Shopping Mall</title>
</head>
<body onload="mainOnload();">
<!-- Navigation -->
<nav class="nav">
    <div class="wrapper container">
        <div class="logo"><a href="main">SM SHOP</a></div>
        <ul class="nav-list">
            <div class="top">
                <label class="btn close-btn"><i class="fas fa-times"></i></label>
            </div>
            <li><a href="main">Home</a></li>
<%--            <li><a href="#" onclick="homeClick()">Home</a></li>--%>
            <li><a href="#" onclick="productsClick()">Products</a></li>
            <li>
                <a href="#" class="desktop-item">Brand <span><i class="fas fa-chevron-down"></i></span></a>
                <input type="checkbox" id="showMega">
                <label for="showMega" class="mobile-item">Brand <span><i class="fas fa-chevron-down"></i></span></label>
                <div class="mega-box">
                    <div class="content">
                        <div class="row">
                            <img src="images/main.png" alt="">
                        </div>
                        <div class="row">
                            <header>Bed</header>
                            <ul class="mega-links">
                                <li><a href="#" onclick='brandClick(1, "HANSSEM")'>HANSSEM</a></li>
                                <li><a href="#" onclick='brandClick(1, "iloom")'>iloom</a></li>
                                <li><a href="#" onclick='brandClick(1, "WELLZ")'>WELLZ</a></li>
                                <li><a href="#" onclick='brandClick(1, "youngDongGagu")'>youngDongGagu</a></li>
                            </ul>
                        </div>
                        <div class="row">
                            <header>Sofa</header>
                            <ul class="mega-links">
                                <li><a href="#" onclick='brandClick(2, "HANSSEM")'>HANSSEM</a></li>
                                <li><a href="#" onclick='brandClick(2, "iloom")'>iloom</a></li>
                                <li><a href="#" onclick='brandClick(2, "WELLZ")'>WELLZ</a></li>
                                <li><a href="#" onclick='brandClick(2, "youngDongGagu")'>youngDongGagu</a></li>
                            </ul>
                        </div>
                        <div class="row">
                            <header>Cabinet</header>
                            <ul class="mega-links">
                                <li><a href="#" onclick='brandClick(3, "HANSSEM")'>HANSSEM</a></li>
                                <li><a href="#" onclick='brandClick(3, "iloom")'>iloom</a></li>
                                <li><a href="#" onclick='brandClick(3, "WELLZ")'>WELLZ</a></li>
                                <li><a href="#" onclick='brandClick(3, "youngDongGagu")'>youngDongGagu</a></li>
                            </ul>
                        </div>

                    </div>
                </div>
            </li>

            <li>
                <a href="#" class="desktop-item">Custom <span><i class="fas fa-chevron-down"></i></span></a>
                <input type="checkbox" id="showdrop2">
                <label for="showdrop2" class="mobile-item">Custom
                    <span><i class="fas fa-chevron-down"></i></span></label>
                <ul class="drop-menu2">
                    <li><a href="#" onclick="historyClick();">Payment History</a></li>
                    <li><a href="#" onclick="qnaClick();">Faq</a></li>
                </ul>
            </li>

            <% if (userVo.getUserLevel() == 1) {%>
            <li class="admin" onclick="adminClick()">
                <a href="#" class="desktop-item">Admin</a>
            </li>
            <% } else { %>
            <li style="display: none"></li>
            <% } %>
            <% if (userVo != null) { %>
            <li class="logout"><a href="#">Log Out</a></li>
            <% } else { %>
            <li class="login"><a href="#">Log In</a></li>
            <% } %>
            <li class="icons">
                    <span>
                        <img src="images/shoppingBag.svg" alt="">
                        <small class="count d-flex">0</small>
                    </span>
<%--                <span><img src="images/search.svg" alt=""></span>--%>
            </li>
        </ul>
        <label class="btn open-btn"><i class="fas fa-bars"></i></label>
    </div>
</nav>
<div id="js-dialog" class="body-item dialog">
    <div class="dialog-item cover"></div>
    <div class="dialog-item window">
        <div class="window-item title"></div>
        <div class="window-item content"></div>
        <div class="window-item buttons"></div>
    </div>
</div>
<div class="js-content visible" data-aos="fade-down" data-aos-duration="2000">
    <!-- Main -->
    <div class="main" data-aos="fade-down" data-aos-duration="2000">
        <div class="left">
            <span>Exclusive Sales</span>
            <h1>UP TO 50% OFF ON SALES</h1>
            <small>Get all exclusive offers for the season</small>
            <a href="#" onclick="productsClick()">View Collection</a>
        </div>
        <div class="right">
            <img src="images/main.png" alt="">
        </div>
    </div>

    <!-- Promotion -->
    <section class="section promotion" data-aos="fade-down" data-aos-duration="2000">
        <div class="title">
            <h2>Shop Collections</h2>
            <span>Select from the preminum product and save plenty meney</span>
        </div>

        <div class="promotion-layout container">
            <div class="promotion-item">
                <img src="images/bg1.jpg" alt="">
                <div class="promotion-content">
                    <h3>Bed Room</h3>
                    <a href="#">SHOP NOW</a>
                </div>
            </div>
            <div class="promotion-item">
                <img src="images/bg2.jpg" alt="">
                <div class="promotion-content">
                    <h3>Living Room</h3>
                    <a href="#">SHOP NOW</a>
                </div>
            </div>
            <div class="promotion-item">
                <img src="images/bg3.jpg" alt="">
                <div class="promotion-content">
                    <h3>Kitchen</h3>
                    <a href="#">SHOP NOW</a>
                </div>
            </div>
        </div>
    </section>

    <!-- Products -->
    <section class="section products" data-aos="fade-down" data-aos-duration="2000">
        <div class="title">
            <h2>New Products</h2>
            <span>Select from the premium product and save plenty money</span>
        </div>
        <div class="product-layout">
            <div class="product">
                <div class="img-container">
                    <img src="images/bed1.jpg" alt="">
                    <div class="addCart">
                        <i class="fas fa-shopping-cart"></i>
                    </div>
                    <ul class="side-icons">
                        <span><i class="fas fa-search"></i></span>
                        <span><i class="far fa-heart"></i></span>
                        <span><i class="fas fa-sliders-h"></i></span>
                    </ul>
                </div>
                <div class="bottom">
                    <a href="#">Drawers Bed</a>
                    <div class="price">
                        <span>₩1,000,000</span>
                    </div>
                </div>
            </div>
            <div class="product">
                <div class="img-container">
                    <img src="images/sofa1.jpg" alt="">
                    <div class="addCart">
                        <i class="fas fa-shopping-cart"></i>
                    </div>
                    <ul class="side-icons">
                        <span><i class="fas fa-search"></i></span>
                        <span><i class="far fa-heart"></i></span>
                        <span><i class="fas fa-sliders-h"></i></span>
                    </ul>
                </div>
                <div class="bottom">
                    <a href="#">Hyacinth Sofa</a>
                    <div class="price">
                        <span>₩1,000,000</span>
                    </div>
                </div>
            </div>
            <div class="product">
                <div class="img-container">
                    <img src="images/book1.jpg" alt="">
                    <div class="addCart">
                        <i class="fas fa-shopping-cart"></i>
                    </div>
                    <ul class="side-icons">
                        <span><i class="fas fa-search"></i></span>
                        <span><i class="far fa-heart"></i></span>
                        <span><i class="fas fa-sliders-h"></i></span>
                    </ul>
                </div>
                <div class="bottom">
                    <a href="#">Book Wood Cabinet</a>
                    <div class="price">
                        <span>₩1,000,000</span>
                    </div>
                </div>
            </div>
            <div class="product">
                <div class="img-container">
                    <img src="images/table1.jpg" alt="">
                    <div class="addCart">
                        <i class="fas fa-shopping-cart"></i>
                    </div>
                    <ul class="side-icons">
                        <span><i class="fas fa-search"></i></span>
                        <span><i class="far fa-heart"></i></span>
                        <span><i class="fas fa-sliders-h"></i></span>
                    </ul>
                </div>
                <div class="bottom">
                    <a href="#">Cuisine Wood Table</a>
                    <div class="price">
                        <span>₩1,000,000</span>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <!-- Advert -->
    <section class="section advert" data-aos="fade-down" data-aos-duration="2000">
        <div class="advert-layout container">
            <div class="item">
                <img src="images/adv2.jpg" alt="">
                <div class="content left">
                    <span>New Trending</span>
                    <h3>American Style</h3>
                    <a href="">Shop Now</a>
                </div>
            </div>
            <div class="item">
                <img src="images/adv3.jpg" alt="">
                <div class="content right">
                    <span>New Trending</span>
                    <h3>Europe Style</h3>
                    <a href="">Shop Now</a>
                </div>
            </div>
        </div>
    </section>

    <!-- Brands -->
    <section class="section brands" data-aos="fade-left" data-aos-duration="2000">
        <div class="title">
            <h2>Shop by Brands</h2>
            <span>Select from the premium product and save plenty money</span>
        </div>
        <div class="brand-layout container">
            <div class="glide" id="glide1">
                <div class="glide__track" data-glide-el="track">
                    <ul class="glide__slides">
                        <li class="glide__slide">
                            <img src="images/han.png" alt=""/>
                        </li>
                        <li class="glide__slide">
                            <img src="images/iloom.png" alt=""/>
                        </li>
                        <li class="glide__slide">
                            <img src="images/wellz.png" alt=""/>
                        </li>
                        <li class="glide__slide">
                            <img src="images/young.png" alt=""/>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </section>
</div>
<div class="js-items" data-aos="fade-down" data-aos-duration="2000"></div>
<div class="js-detail" data-aos="fade-down" data-aos-duration="2000">
    <section class="section product-details">
        <div class="details container" id="details">
        </div>
    </section>
</div>
<div class="js-cart" data-aos="fade-down" data-aos-duration="2000">
</div>
<div class="js-qna" data-aos="fade-down" data-aos-duration="2000">
</div>
<!-- Footer -->
<footer id="footer" class="section footer">
    <div class="container">
        <div class="footer-container">
            <div class="footer-center">
                <h3>EXTRAS</h3>
                <a href="#">Brands</a>
                <a href="#">Gift Certificates</a>
                <a href="#">Affiliate</a>
                <a href="#">Specials</a>
                <a href="#">Site Map</a>
            </div>
            <div class="footer-center">
                <h3>INFORMATION</h3>
                <a href="#">About Us</a>
                <a href="#">Privacy Policy</a>
                <a href="#">Terms & Conditions</a>
                <a href="#">Contact Us</a>
                <a href="#">Site Map</a>
            </div>
            <div class="footer-center">
                <h3>MY ACCOUNT</h3>
                <a href="#">My Account</a>
                <a href="#">Order History</a>
                <a href="#">Wish List</a>
                <a href="#">Newsletter</a>
                <a href="#">Returns</a>
            </div>
            <div class="footer-center">
                <h3>CONTACT US</h3>
                <div>
                    <span>
                    <i class="fas fa-map-marker-alt"></i>
                    </span>
                    Daegu, Korea
                </div>
                <div>
                    <span>
                    <i class="far fa-envelope"></i>
                    </span>
                    kainbr@naver.com
                </div>
                <div>
                    <span>
                    <i class="fas fa-phone"></i>
                    </span>
                    010-XXXX-1829
                </div>
            </div>
        </div>
    </div>
</footer>

<!-- Glidejs -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/Glide.js/3.4.1/glide.min.js"></script>
<!-- Gsap -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.5.1/gsap.min.js"></script>
<%-- AOS --%>
<script src="https://unpkg.com/aos@next/dist/aos.js"></script>
<!-- Custom Script -->
<script src="scripts/slider.js"></script>
<script src="scripts/main.js"></script>
<script src="scripts/ajax.js"></script>
<script src="scripts/dialog.js"></script>
<script src="scripts/goto.js"></script>
<script src="scripts/items.js"></script>
<script src="scripts/detail.js"></script>
<script src="scripts/cart.js"></script>
<script src="scripts/sort.js"></script>
<script src="scripts/latest.js"></script>
<script src="scripts/brand.js"></script>
<script src="scripts/cart-history.js"></script>
<script src="scripts/qna.js"></script>
</body>
</html>
