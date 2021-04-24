package service.impl;

import org.springframework.stereotype.Service;
import pojo.Product;
import service.ProductService;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Override
    public List<Product> getProductList() {
        return Arrays.asList(
                new Product(1, "苹果电脑", 10, new BigDecimal("20000")),
                new Product(2, "戴尔电脑", 20, new BigDecimal("10000"))
        );
    }
}
