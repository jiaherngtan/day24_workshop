package com.vttp2022.day24_workshop.controllers;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.vttp2022.day24_workshop.models.Order;
import com.vttp2022.day24_workshop.models.Product;
import com.vttp2022.day24_workshop.services.OrderService;

import jakarta.servlet.http.HttpSession;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping(path = "/order")
    public String addToOrder(
            @RequestBody MultiValueMap<String, String> form,
            Model model,
            HttpSession session) {

        Order o = (Order) session.getAttribute("orderForSession");
        List<Product> products = (List<Product>) session.getAttribute("productsForSession");
        if (null == products) {
            System.out.println("This is a new session");
            System.out.printf("Session id = %s\n", session.getId());
            products = new LinkedList<>();
            session.setAttribute("productsForSession", products);
        }

        if (null == o) {
            o = new Order();
            session.setAttribute("orderForSession", o);
        }

        String orderDate = form.getFirst("date");
        String customerName = form.getFirst("name");
        String shipAddress = form.getFirst("address");
        String notes = form.getFirst("notes");
        Float tax = Float.parseFloat(form.getFirst("tax"));
        String productName = form.getFirst("product");
        Float unitPrice = Float.parseFloat(form.getFirst("price"));
        Float discount = Float.parseFloat(form.getFirst("discount"));
        int quantity = Integer.parseInt(form.getFirst("quantity"));

        Product p = new Product();
        p.setProductName(productName);
        p.setUnitPrice(unitPrice);
        p.setDiscount(discount);
        p.setQuantity(quantity);
        products.add(p);

        o.setOrderDate(orderDate);
        o.setCustomerName(customerName);
        o.setShipAddress(shipAddress);
        o.setNotes(notes);
        o.setTax(tax);
        o.setProducts(products);

        model.addAttribute("products", products);

        return "order";
    }

    @PostMapping(path = "/addOrder")
    public String additionalOrder(
            @RequestBody MultiValueMap<String, String> form,
            Model model,
            HttpSession session) {

        Order o = (Order) session.getAttribute("orderForSession");
        List<Product> products = (List<Product>) session.getAttribute("productsForSession");

        String productName = form.getFirst("product");
        Float unitPrice = Float.parseFloat(form.getFirst("price"));
        Float discount = Float.parseFloat(form.getFirst("discount"));
        int quantity = Integer.parseInt(form.getFirst("quantity"));

        Product p = new Product();
        p.setProductName(productName);
        p.setUnitPrice(unitPrice);
        p.setDiscount(discount);
        p.setQuantity(quantity);
        products.add(p);
        o.setProducts(products);

        model.addAttribute("products", products);

        return "order";
    }

    @PostMapping(path = "/checkout")
    public String checkout(
            Model model,
            HttpSession session) {

        Order o = (Order) session.getAttribute("orderForSession");
        List<Product> products = (List<Product>) session.getAttribute("productsForSession");

        orderService.createOrder(o);

        model.addAttribute("orderId", o.getOrderId());
        model.addAttribute("products", products);

        return "checkout";
    }

}
