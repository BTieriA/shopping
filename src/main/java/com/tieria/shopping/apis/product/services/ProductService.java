package com.tieria.shopping.apis.product.services;

import com.tieria.shopping.apis.product.containers.*;
import com.tieria.shopping.apis.product.daos.ProductDao;
import com.tieria.shopping.apis.product.enums.CartResult;
import com.tieria.shopping.apis.product.enums.ImageResult;
import com.tieria.shopping.apis.product.enums.ProductResult;
import com.tieria.shopping.apis.product.vos.*;
import com.tieria.shopping.common.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.sql.DataSource;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;


@Service
public class ProductService {
    private final DataSource dataSource;
    private final ProductDao productDao;

    @Autowired
    public ProductService(DataSource dataSource, ProductDao productDao) {
        this.dataSource = dataSource;
        this.productDao = productDao;
    }

    //    -------------------------------------------------------------------------------------------- CREATE (insert)
    public ProductResultContainer addProduct(UserVo userVo, AddProductVo addProductVo) throws SQLException {
        int index = -1;
        if (addProductVo.getPdtPrice() < 0) {
            return new ProductResultContainer(ProductResult.INVALID);
        }
        if (userVo == null || (userVo.getUserLevel() != 1)) {
            return new ProductResultContainer(ProductResult.NOT_ALLOWED);
        }
        try (Connection connection = this.dataSource.getConnection()) {
            this.productDao.insertProduct(connection, addProductVo);
            index = this.productDao.getLastIndex(connection);
            if (index < 0) {
                return new ProductResultContainer(ProductResult.INVALID);
            } else {
                return new ProductResultContainer(ProductResult.SUCCESS, index);
            }
        }
    }

    public ImageResult addImage(AddImageVo addImageVo) throws SQLException {
        try (Connection connection = this.dataSource.getConnection()) {
            if (addImageVo.getProductIndex() < 0) {
                return ImageResult.IMAGE_UPLOAD_FAILURE;
            } else {
                this.productDao.insertImage(connection, addImageVo);
                return ImageResult.IMAGE_UPLOAD_SUCCESS;
            }
        }
    }

    public ProductResult addColorSize(AddColorSizeVo addColorSizeVo, UserVo userVo) throws SQLException {
        if (userVo.getUserIndex() != addColorSizeVo.getUserIndex()) {
            return ProductResult.INVALID;
        }
        try (Connection connection = this.dataSource.getConnection()) {
            if (addColorSizeVo.getColor().isEmpty() || addColorSizeVo.getSize().isEmpty() ||
                    addColorSizeVo.getItemIndex() < 0 || addColorSizeVo.getImgIndex() < 0) {
                return ProductResult.FAILURE;
            } else {
                this.productDao.insertDetail(connection, addColorSizeVo);
                this.productDao.insertCart(connection, addColorSizeVo);
                return ProductResult.SUCCESS;
            }
        }
    }


    //    -------------------------------------------------------------------------------------------- READ (select)
    // List - Total
    public ProductListContainer getSortList(int page, SortVo sortVo) throws SQLException {
        try (Connection connection = this.dataSource.getConnection()) {
            ArrayList<ProductVo> itemList = new ArrayList<>();
            ArrayList<ProductVo> products = this.productDao.sortList(connection, page, sortVo);
            if (products != null) {
                for (ProductVo item : products) {
                    itemList.add(new ProductVo(
                            item.getPdtIndex(),
                            item.getPdtBrand(),
                            item.getPdtName(),
                            item.getPdtPrice(),
                            item.getPdtKinds(),
                            item.getPdtDetail(),
                            item.getPdtDate(),
                            item.getPdtImage()
                    ));
                }
                return new ProductListContainer(ProductResult.SUCCESS, itemList);
            } else {
                return new ProductListContainer(ProductResult.FAILURE, null);
            }

        }
    }

    public ProductListContainer getProductsList(int page) throws SQLException {
        try (Connection connection = this.dataSource.getConnection()) {
            ArrayList<ProductVo> itemList = new ArrayList<>();
            ArrayList<ProductVo> products = this.productDao.productsList(connection, page);
            if (products != null) {
                for (ProductVo item : products) {
                    itemList.add(new ProductVo(
                            item.getPdtIndex(),
                            item.getPdtBrand(),
                            item.getPdtName(),
                            item.getPdtPrice(),
                            item.getPdtKinds(),
                            item.getPdtDetail(),
                            item.getPdtDate(),
                            item.getPdtImage()
                    ));
                }
                return new ProductListContainer(ProductResult.SUCCESS, itemList);
            } else {
                return new ProductListContainer(ProductResult.FAILURE, null);
            }

        }
    }

    // Get Image - Image(byte[]) -> byte[]
    public byte[] getImage(int index) throws SQLException, IOException {
        try (Connection connection = this.dataSource.getConnection()) {
            byte[] imageOutput = null;

            byte[] imagesDao = this.productDao.getImage(connection, index);

            BufferedImage bufferedImageDao = ImageIO.read(new ByteArrayInputStream(imagesDao));
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImageDao, "jpg", byteArrayOutputStream);
            imageOutput = byteArrayOutputStream.toByteArray();

            return imageOutput;
        }
    }



    // Get Total Count
    public int getTotalCount() throws SQLException {
        try (Connection connection = this.dataSource.getConnection()) {
            return this.productDao.getTotalProducts(connection);
        }
    }

    // Get Product Detail
    public ProductDetailResultContainer getDetail(int index) throws SQLException {
        try (Connection connection = this.dataSource.getConnection()) {
            ProductVo productVo = this.productDao.getDetail(connection, index);
            if (productVo == null) {
                return new ProductDetailResultContainer(ProductResult.FAILURE, null);
            } else {
                return new ProductDetailResultContainer(ProductResult.SUCCESS, productVo);
            }
        }
    }

    // Get Related Product
    public ProductListContainer getRelate(int kinds) throws SQLException {
        try (Connection connection = this.dataSource.getConnection()) {
            ArrayList<ProductVo> relateProductResult = new ArrayList<>();
            ArrayList<ProductVo> relateProductDao = this.productDao.relatedProduct(connection, kinds);
            if (relateProductDao != null) {
                for (ProductVo relateProduct : relateProductDao) {
                    relateProductResult.add(new ProductVo(
                            relateProduct.getPdtIndex(),
                            relateProduct.getPdtBrand(),
                            relateProduct.getPdtName(),
                            relateProduct.getPdtPrice(),
                            relateProduct.getPdtKinds(),
                            relateProduct.getPdtDetail(),
                            relateProduct.getPdtDate(),
                            relateProduct.getPdtImage()
                    ));
                }
                return new ProductListContainer(ProductResult.SUCCESS, relateProductResult);
            } else {
                return new ProductListContainer(ProductResult.FAILURE, null);
            }
        }
    }

    // get color size
    public CartListContainer getColorSize(UserVo userVo) throws SQLException {
        if (userVo == null) {
            return new CartListContainer(CartResult.INVALID, null);
        }
        try (Connection connection = this.dataSource.getConnection()) {
            ArrayList<CartVo> colorSizeResult = new ArrayList<>();
            ArrayList<CartVo> colorSizeDao = this.productDao.getColorSize(connection, userVo);
            if (colorSizeDao != null) {
                for (CartVo cart : colorSizeDao) {
                    colorSizeResult.add(new CartVo(
                            cart.getImgIndex(),
                            cart.getItemName(),
                            cart.getItemBrand(),
                            cart.getItemColor(),
                            cart.getItemSize(),
                            cart.getItemPrice(),
                            cart.getItemDate()
                    ));
                }
                return new CartListContainer(CartResult.SUCCESS, colorSizeResult);
            } else {
                return new CartListContainer(CartResult.FAILURE, null);
            }
        }
    }

    // Get latest Product
    public ProductListContainer getLatest() throws SQLException {
        try (Connection connection = this.dataSource.getConnection()) {
            ArrayList<ProductVo> latestProductResult = new ArrayList<>();
            ArrayList<ProductVo> latestProductDao = this.productDao.latestProduct(connection);
            if (latestProductDao != null) {
                for (ProductVo latestProduct : latestProductDao) {
                    latestProductResult.add(new ProductVo(
                            latestProduct.getPdtIndex(),
                            latestProduct.getPdtBrand(),
                            latestProduct.getPdtName(),
                            latestProduct.getPdtPrice(),
                            latestProduct.getPdtKinds(),
                            latestProduct.getPdtDetail(),
                            latestProduct.getPdtDate(),
                            latestProduct.getPdtImage()
                    ));
                }
                return new ProductListContainer(ProductResult.SUCCESS, latestProductResult);
            } else {
                return new ProductListContainer(ProductResult.FAILURE, null);
            }
        }
    }

    // Get Brand Product
    public ProductListContainer getBrand(BrandVo brandVo) throws SQLException {
        try (Connection connection = this.dataSource.getConnection()) {
            ArrayList<ProductVo> brandProductResult = new ArrayList<>();
            ArrayList<ProductVo> brandProductDao = this.productDao.brandProduct(connection, brandVo);
            if (brandProductDao != null) {
                for (ProductVo brandProduct : brandProductDao) {
                    brandProductResult.add(new ProductVo(
                            brandProduct.getPdtIndex(),
                            brandProduct.getPdtBrand(),
                            brandProduct.getPdtName(),
                            brandProduct.getPdtPrice(),
                            brandProduct.getPdtKinds(),
                            brandProduct.getPdtDetail(),
                            brandProduct.getPdtDate(),
                            brandProduct.getPdtImage()
                    ));
                }
                return new ProductListContainer(ProductResult.SUCCESS, brandProductResult);
            } else {
                return new ProductListContainer(ProductResult.FAILURE, null);
            }
        }
    }

    // get cart history
    public CartListContainer getCartHistory(UserVo userVo, int page) throws SQLException {
        if (userVo == null) {
            return new CartListContainer(CartResult.INVALID, null);
        }
        try (Connection connection = this.dataSource.getConnection()) {
            ArrayList<CartVo> cartHistoryResult = new ArrayList<>();
            ArrayList<CartVo> cartHistoryDao = this.productDao.getCartHistory(connection, userVo, page);
            if (cartHistoryDao != null) {
                for (CartVo cart : cartHistoryDao) {
                    cartHistoryResult.add(new CartVo(
                            cart.getImgIndex(),
                            cart.getItemName(),
                            cart.getItemBrand(),
                            cart.getItemColor(),
                            cart.getItemSize(),
                            cart.getItemPrice(),
                            cart.getItemDate()
                    ));
                }
                return new CartListContainer(CartResult.SUCCESS, cartHistoryResult);
            } else {
                return new CartListContainer(CartResult.FAILURE, null);
            }
        }
    }

    // get cart total history
    public CartTotalListContainer getCartTotalHistory(UserVo userVo, int page) throws SQLException {
        if (userVo == null || userVo.getUserLevel() != 1) {
            return new CartTotalListContainer(CartResult.INVALID, null);
        }
        try (Connection connection = this.dataSource.getConnection()) {
            ArrayList<CartTotalVo> cartHistoryResult = new ArrayList<>();
            ArrayList<CartTotalVo> cartHistoryDao = this.productDao.getCartTotalHistory(connection, page);
            if (cartHistoryDao != null) {
                for (CartTotalVo cart : cartHistoryDao) {
                    cartHistoryResult.add(new CartTotalVo(
                            cart.getImgIndex(),
                            cart.getItemName(),
                            cart.getItemBrand(),
                            cart.getItemColor(),
                            cart.getItemSize(),
                            cart.getItemPrice(),
                            cart.getItemDate(),
                            cart.getUserName()
                    ));
                }
                return new CartTotalListContainer(CartResult.SUCCESS, cartHistoryResult);
            } else {
                return new CartTotalListContainer(CartResult.FAILURE, null);
            }
        }
    }

    // Get Total Carts
    public int getTotalCarts() throws SQLException {
        try (Connection connection = this.dataSource.getConnection()) {
            return this.productDao.getTotalCarts(connection);
        }
    }

    // Get Total User Count
    public int getTotalUserCarts(UserVo userVo) throws SQLException {
        try (Connection connection = this.dataSource.getConnection()) {
            return this.productDao.getTotalUserCarts(connection, userVo);
        }
    }


    //    -------------------------------------------------------------------------------------------- UPDATE
    public ProductResult updateProduct(UserVo userVo, UpdateProductVo updateProductVo) throws SQLException {
        if (updateProductVo.getPdtPrice() < 0) {
            return ProductResult.INVALID;
        }
        if (userVo == null || (userVo.getUserLevel() != 1)) {
            return ProductResult.NOT_ALLOWED;
        }
        try (Connection connection = this.dataSource.getConnection()) {
            if (this.productDao.updateProduct(connection, updateProductVo) != 1){
                return ProductResult.FAILURE;
            } else {
                return ProductResult.SUCCESS;
            }

        }
    }

    //    -------------------------------------------------------------------------------------------- DELETE
    // each cart list delete
    public CartResult deleteCart(UserVo userVo, int index) throws SQLException {
        if (userVo == null) {
            return CartResult.INVALID;
        }
        try (Connection connection = this.dataSource.getConnection()) {
            if (this.productDao.deleteCart(connection, index) == 0) {
                return CartResult.FAILURE;
            } else {
                this.productDao.deleteFinalCart(connection, index);
                return CartResult.SUCCESS;
            }

        }
    }

    // all cart list delete
    public CartResult deleteAllCart(UserVo userVo) throws SQLException {
        if (userVo == null) {
            return CartResult.INVALID;
        }
        try (Connection connection = this.dataSource.getConnection()) {
            if (this.productDao.deleteAllCart(connection) == 0) {
                return CartResult.FAILURE;
            } else {
                return CartResult.SUCCESS;
            }
        }
    }

    // delete product
    public ProductResult deleteProduct(UserVo userVo, int index) throws SQLException {
        if (userVo == null || userVo.getUserLevel() != 1) {
            return ProductResult.INVALID;
        }
        try(Connection connection = this.dataSource.getConnection()) {
            if (this.productDao.deleteProduct(connection, index) == 0) {
                return ProductResult.FAILURE;
            } else {
                return ProductResult.SUCCESS;
            }
        }
    }
}
