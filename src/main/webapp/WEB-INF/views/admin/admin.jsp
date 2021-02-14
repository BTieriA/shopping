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
<body>
<nav class="nav admin-nav">
    <div class="wrapper container">
        <div class="logo admin-logo"><a href="main">ADMIN</a></div>
        <ul class="nav-list admin-nav-list">
            <div class="top">
                <label class="btn close-btn"><i class="fas fa-times"></i></label>
            </div>
            <li><a href="#" onclick="adminClick()">Home</a></li>
            <li><a href="#" onclick="adminItemsClick()">Show Products</a></li>
            <li onclick="insertProduct()">
                <a href="#" class="desktop-item">Insert Product</a>
                <input type="checkbox" id="showMega">
                <label for="showMega" class="mobile-item">Insert</label>
            </li>
            <li>
                <a href="#" class="desktop-item">Custom <span><i class="fas fa-chevron-down"></i></span></a>
                <input type="checkbox" id="showdrop2">
                <label for="showdrop2" class="mobile-item">Custom
                    <span><i class="fas fa-chevron-down"></i></span></label>
                <ul class="drop-menu2">
                    <li><a href="#" onclick="historyClick();">Payment History</a></li>
                    <li><a href="#" onclick="qnaListClick();">Faq</a></li>
                </ul>
            </li>

            <% if (userVo.getUserLevel() == 1) {%>
            <li class="admin">
                <a href="/main" class="desktop-item">Customer</a>
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

<div class="js-admin visible" data-aos="fade-down" data-aos-duration="2000">
    <div class="main" data-aos="fade-down" data-aos-duration="500">
        <div class="left">
            <span>Product Modify Mode</span>
            <h1>Administrator</h1>
            <small>This site is authorized by administrator</small>
            <a href="#" onclick="adminItemsClick();">Show Products</a>
        </div>
        <div class="right">
            <img class="adminMain" src="admin/images/admin.svg" alt="">
        </div>
    </div>
</div>

<div class="js-addition" data-aos="fade-down" data-aos-duration="2000">
    <div class="section product-insert" data-aos="fade-down" data-aos-duration="2000">
        <form class="details container" id="addForm" method="POST" action="apis/product/addProduct"
              enctype="multipart/form-data">
            <div class="left" data-aos="fade-right" data-aos-duration="2000">
                <div class="fileBox">
                    <input type="text" class="uploadName" value="Upload File" disabled="disabled"/>
                    <label for="imgUpload" onclick="uploadClick()">Upload</label>
                    <input type="file" name="image" id="imgUpload" accept="image/*" multiple/>
                    <img src="admin/images/default.jpg" class="preview"/>
                </div>
            </div>
            <div class="right">
                <div class="brandBox" data-aos="fade-left" data-aos-duration="2000">
                    <select id="brand" name="brand">
                        <option value="Select Brands" selected disabled>SELECT BRANDS</option>
                        <option value="HANSSEM">HANSSEM</option>
                        <option value="iloom">iloom</option>
                        <option value="WELLZ">WELLZ</option>
                        <option value="youngDongGagu">youngDongGagu</option>
                    </select>
                    <span><i class="fas fa-chevron-down"></i></span>
                </div>
                <div class="name">
                    <input type="text" id="name" name="name" placeholder="PRODUCT NAME">
                </div>
                <div class="price">
                    <input type="text" id="price" name="price" placeholder="PRICE">
                </div>
                <div class="kindsBox">
                    <select name="kinds">
                        <option value="Select Kinds" selected disabled>SELECT KINDS</option>
                        <option value="1">BED</option>
                        <option value="2">SOFA</option>
                        <option value="3">CABINET</option>
                        <option value="4">TABLE</option>
                        <option value="5">CHAIR</option>
                        <option value="6">SINK</option>
                        <option value="7">ETC</option>
                    </select>
                    <span><i class="fas fa-chevron-down"></i></span>
                </div>
                <h3>Product Detail</h3>
                <textarea cols="60" rows="10" name="detail"></textarea>
                <div class="submitBox">
                    <input type="submit" class="addProduct" value="Add Product">
                </div>
            </div>
        </form>
    </div>
</div>

<div class="js-items" data-aos="fade-down" data-aos-duration="2000">
    <!-- Products -->
    <section class="section products" data-aos="fade-down" data-aos-duration="2000">
        <div class="container products-layout">
            <div class="col-1-of-4" data-aos="fade-right" data-aos-duration="2000">
                <div>
                    <div class="block-title">
                        <h3>Category</h3>
                    </div>
                    <ul class="block-content">
                        <li>
                            <input class="cateKinds" type="checkbox" name="cateKinds" value="1" checked
                                   onchange="checkCategory()">
                            <label>
                                <span>Beds</span>
                            </label>
                        </li>
                        <li>
                            <input class="cateKinds" type="checkbox" name="cateKinds" value="2" checked
                                   onchange="checkCategory()">
                            <label>
                                <span>Sofas</span>
                            </label>
                        </li>
                        <li>
                            <input class="cateKinds" type="checkbox" name="cateKinds" value="3" checked
                                   onchange="checkCategory()">
                            <label>
                                <span>Cabinets</span>
                            </label>
                        </li>
                        <li>
                            <input class="cateKinds" type="checkbox" name="cateKinds" value="4" checked
                                   onchange="checkCategory()">
                            <label>
                                <span>Tables</span>
                            </label>
                        </li>
                        <li>
                            <input class="cateKinds" type="checkbox" name="cateKinds" value="5" checked
                                   onchange="checkCategory()">
                            <label>
                                <span>Chairs</span>
                            </label>
                        </li>
                        <li>
                            <input class="cateKinds" type="checkbox" name="cateKinds" value="6" checked
                                   onchange="checkCategory()">
                            <label>
                                <span>Sink</span>
                            </label>
                        </li>
                    </ul>
                </div>
            </div>

            <div class="col-3-of-4" data-aos="fade-left" data-aos-duration="2000">
                <form name="sortForm" method="POST" action="apis/product/productList">
                    <div class="item">
                        <label>Sort By</label>
                        <select name="sort-by">
                            <option value="product_name">Name</option>
                            <option value="product_price">Price</option>
                            <option value="product_date">Newness</option>
                        </select>
                    </div>
                    <div class="item">
                        <label>Order</label>
                        <select name="order">
                            <option value="ASC" selected="selected">ASC</option>
                            <option value="DESC">DESC</option>
                        </select>
                    </div>

                    <a href="#">Apply</a>
                </form>
                <div class="product-layout">
                    <div class="product">
                        <div class="img-container">
                            <a href="#"><img src="../images/bed1.jpg" alt=""></a>

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
                </div>

                <!-- Pagination -->
                <ul class="pagination">
                    <span>1</span>
                    <span>2</span>
                    <span class="icon">></span>
                    <span class="last">last >></span>
                </ul>
            </div>
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
<script src="scripts/main.js"></script>
<script src="scripts/admin.js"></script>
<script src="scripts/ajax.js"></script>
<script src="admin/scripts/adminItems.js"></script>
<script src="scripts/dialog.js"></script>

<script src="scripts/slider.js"></script>
<script src="admin/scripts/adminGoto.js"></script>
<script src="scripts/cart.js"></script>
<script src="admin/scripts/adminSort.js"></script>
<script src="scripts/latest.js"></script>
<script src="scripts/brand.js"></script>
<script src="admin/scripts/adminHistory.js"></script>
<script src="admin/scripts/adminQna.js"></script>
<script src="admin/scripts/adminUpdate.js"></script>
<script src="admin/scripts/adminAnswer.js"></script>
<script src="admin/scripts/adminAnsList.js"></script>
</body>
</html>
