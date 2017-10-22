package com.ngbfintech2.api.dto;

public class ResponseProductDetails {

    String productId;

    String productName;

    String productDesc;

    String productImg;

    String productPrice;

    String productGuarantee;

    public ResponseProductDetails() {
    }

    public ResponseProductDetails(String productId, String productName, String productDesc, String productImg, String productPrice, String productGuarantee) {
        this.productId = productId;
        this.productName = productName;
        this.productDesc = productDesc;
        this.productImg = productImg;
        this.productPrice = productPrice;
        this.productGuarantee = productGuarantee;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductGuarantee() {
        return productGuarantee;
    }

    public void setProductGuarantee(String productGuarantee) {
        this.productGuarantee = productGuarantee;
    }
}
