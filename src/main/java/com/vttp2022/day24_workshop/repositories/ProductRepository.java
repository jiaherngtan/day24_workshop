package com.vttp2022.day24_workshop.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.vttp2022.day24_workshop.models.Product;
import static com.vttp2022.day24_workshop.repositories.Queries.*;

@Repository
public class ProductRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addProduct(List<Product> products, String orderId) {

        List<Object[]> data = products.stream()
                .map(p -> {
                    Object[] o = new Object[5];
                    o[0] = p.getProductName();
                    o[1] = p.getUnitPrice();
                    o[2] = p.getDiscount();
                    o[3] = p.getQuantity();
                    o[4] = orderId;
                    return o;
                }).toList();

        jdbcTemplate.batchUpdate(SQL_INSERT_PRODUCT, data);
    }

}
