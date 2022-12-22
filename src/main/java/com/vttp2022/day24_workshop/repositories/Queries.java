package com.vttp2022.day24_workshop.repositories;

public class Queries {

    public static final String SQL_INSERT_ORDER = "INSERT INTO orders(order_id,order_date,customer_name,ship_address,notes,tax) VALUES(?, ?, ?, ?, ?, ?)";

    public static final String SQL_INSERT_PRODUCT = "INSERT INTO order_details(product,unit_price,discount,quantity,order_id) VALUES(?, ?, ?, ?, ?)";

}
