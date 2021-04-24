package org.example.pojo;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class Order implements Serializable {
    public Order(Integer id, String address, BigDecimal totolPrice, List<Product> productList) {
        this.id = id;
        this.address = address;
        this.totolPrice = totolPrice;
        this.productList = productList;
    }

    private Integer id;

    private String address;

    private BigDecimal totolPrice;

    private List<Product> productList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getTotolPrice() {
        return totolPrice;
    }

    public void setTotolPrice(BigDecimal totolPrice) {
        this.totolPrice = totolPrice;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
