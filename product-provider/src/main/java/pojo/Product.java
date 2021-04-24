package pojo;

import java.io.Serializable;
import java.math.BigDecimal;

public class Product implements Serializable {
    public Product(Integer id, String productName, Integer productNum, BigDecimal prise) {
        this.id = id;
        this.productName = productName;
        this.productNum = productNum;
        this.prise = prise;
    }

    private Integer id;

    private String productName;

    private Integer productNum;

    private BigDecimal prise;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductNum() {
        return productNum;
    }

    public void setProductNum(Integer productNum) {
        this.productNum = productNum;
    }

    public BigDecimal getPrise() {
        return prise;
    }

    public void setPrise(BigDecimal prise) {
        this.prise = prise;
    }
}
