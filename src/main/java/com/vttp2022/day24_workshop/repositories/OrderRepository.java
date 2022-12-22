package com.vttp2022.day24_workshop.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.vttp2022.day24_workshop.models.Order;
import static com.vttp2022.day24_workshop.repositories.Queries.*;

@Repository
public class OrderRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addOrder(Order o) {

        jdbcTemplate.update(SQL_INSERT_ORDER,
                o.getOrderId(),
                o.getOrderDate(),
                o.getCustomerName(),
                o.getShipAddress(),
                o.getNotes(),
                o.getTax());
    }

}
