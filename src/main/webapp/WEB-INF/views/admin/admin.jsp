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
    <!-- Custom StyleSheet -->
    <link rel="stylesheet" href="styles/common.css">
    <title>SM Shopping Mall</title>
</head>
<body>
<!-- Navigation -->
<nav class="nav">
    <div class="wrapper container">
        <div class="logo"><a href="main">Administrator</a></div>
        <ul class="nav-list">
            <div class="top">
                <label class="btn close-btn"><i class="fas fa-times"></i></label>
            </div>
            <li><a href="#" class="home">Home</a></li>
            <li><a href="#">Show Product</a></li>
            <li class="insertProduct">
                <a href="#" class="desktop-item">Insert Product</a>
                <input type="checkbox" id="showMega">
                <label for="showMega" class="mobile-item">Insert</label>
            </li>

            <li>
                <a href="#" class="desktop-item">Delete Product</a>
                <input type="checkbox" id="showdrop2">
                <label for="showdrop2" class="mobile-item">Delete</label>
            </li>

            <% if (userVo.getUserLevel() == 1) {%>
            <li>
                <a href="#" class="desktop-item" onclick="adminClick()">Admin</a>
            </li>
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
                <span><img src="images/search.svg" alt=""></span>
            </li>
        </ul>
        <label class="btn open-btn"><i class="fas fa-bars"></i></label>
    </div>
</nav>

<div class="js-admin">
    <div class="main">
        <div class="left">
            <span>Product Modify Mode</span>
            <h1>Administrator</h1>
            <small>This site is authorized by administrator</small>
            <a href="#">Show Products</a>
        </div>
        <div class="right">
            <img class="adminMain" src="admin/images/admin.svg" alt="">
        </div>
    </div>
</div>

<div class="js-addition">
    <div class="section product-insert">
        <form class="details container" id="addForm" method="POST" action="apis/product/addProduct"
              enctype="multipart/form-data">
            <div class="left">
                <div class="fileBox">
                    <input type="text" class="uploadName" value="Upload File" disabled="disabled">
                    <label for="imgUpload" onclick="uploadClick()">Upload</label>
                    <input type="file" name="image" id="imgUpload" accept="image/*" multiple />
                    <img src="admin/images/default.jpg" class="preview">
                </div>
            </div>
            <div class="right">
                <div class="room">
                    <input type="text" id="room" name="room" placeholder="ROOM LOCATION">
                </div>
                <div class="name">
                    <input type="text" id="name" name="name" placeholder="PRODUCT NAME">
                </div>
                <div class="price">
                    <input type="text" id="price" name="price" placeholder="PRICE">
                </div>
                <div class="sizeBox">
                    <select name="size">
                        <option value="Select Size" selected disabled>Select Size</option>
                        <option value="S">S</option>
                        <option value="M">M</option>
                        <option value="L">L</option>
                        <option value="XL">XL</option>
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

<div class="js-items">
    <!-- Products -->
    <section class="section products">
        <div class="products-layout container">
            <div class="col-1-of-4">
                <div>
                    <div class="block-title">
                        <h3>Category</h3>
                    </div>
                    <ul class="block-content">
                        <li>
                            <input type="checkbox">
                            <label >
                                <span>Beds</span>
                                <small>(1)</small>
                            </label>
                        </li>
                        <li>
                            <input type="checkbox">
                            <label >
                                <span>Sofas</span>
                                <small>(1)</small>
                            </label>
                        </li>
                        <li>
                            <input type="checkbox">
                            <label >
                                <span>Cabinets</span>
                                <small>(1)</small>
                            </label>
                        </li>
                        <li>
                            <input type="checkbox">
                            <label >
                                <span>tables</span>
                                <small>(1)</small>
                            </label>
                        </li>
                    </ul>
                </div>

                <div>
                    <div class="block-title">
                        <h3>Brands</h3>
                    </div>
                    <ul class="block-content">
                        <li>
                            <input type="checkbox" >
                            <label >
                                <span>HANSSEM</span>
                                <small>(1)</small>
                            </label>
                        </li>
                        <li>
                            <input type="checkbox" >
                            <label >
                                <span>illoom</span>
                                <small>(1)</small>
                            </label>
                        </li>
                        <li>
                            <input type="checkbox" >
                            <label >
                                <span>WELLZ</span>
                                <small>(1)</small>
                            </label>
                        </li>
                        <li>
                            <input type="checkbox" >
                            <label >
                                <span>youngDongGagu</span>
                                <small>(1)</small>
                            </label>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="col-3-of-4">
                <form action="">
                    <div class="item">
                        <label >Sort By</label>
                        <select name="sort-by" >
                            <option value="title">Name</option>
                            <option value="number">Price</option>
                            <option value="search_api_relevance">Relevance</option>
                            <option value="created">Newness</option>
                        </select>
                    </div>
                    <div class="item">
                        <label >Order</label>
                        <select name="sort-by" >
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
                    <div class="product">
                        <div class="img-container">
                            <img src="../images/sofa1.jpg" alt="">
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
                            <img src="../images/book1.jpg" alt="">
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
                            <img src="../images/table1.jpg" alt="">
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
                    <div class="product">
                        <div class="img-container">
                            <img src="../images/bed1.jpg" alt="">
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
                            <img src="../images/sofa1.jpg" alt="">
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
                            <img src="../images/book1.jpg" alt="">
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
                            <img src="../images/table1.jpg" alt="">
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
    <!-- Custom Script -->
    <script src="scripts/main.js"></script>
    <script src="scripts/admin.js"></script>
    <script src="scripts/ajax.js"></script>
    <script src="admin/scripts/items.js"></script>
</body>
</html>
