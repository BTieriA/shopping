package com.tieria.shopping.apis.product.containers;

import com.tieria.shopping.apis.product.enums.ImageResult;
import com.tieria.shopping.apis.product.vos.ImageVo;

import java.util.ArrayList;

public class ImageListContainer {
    private final ImageResult imageResult;
    private final ArrayList<ImageVo> images;
    private final byte[] imageData;

    public ImageListContainer(ImageResult imageResult) {
        this.imageResult = imageResult;
        this.images = null;
        this.imageData = null;
    }

    public ImageListContainer(ImageResult imageResult, ArrayList<ImageVo> images, byte[] imageData) {
        this.imageResult = imageResult;
        this.images = images;
        this.imageData = imageData;
    }

    public ImageResult getImageResult() {
        return imageResult;
    }

    public ArrayList<ImageVo> getImages() {
        return images;
    }

    public byte[] getImageData() {
        return imageData;
    }
}
