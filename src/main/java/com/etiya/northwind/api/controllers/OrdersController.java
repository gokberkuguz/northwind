package com.etiya.northwind.api.controllers;


import com.etiya.northwind.business.abstracts.OrderService;
import com.etiya.northwind.business.requests.orderRequests.CreateOrderRequest;
import com.etiya.northwind.business.requests.orderRequests.UpdateOrderRequest;
import com.etiya.northwind.business.responses.orders.OrderListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrdersController {
    private OrderService orderService;

    @Autowired
    public OrdersController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/getall")
    public List<OrderListResponse> getAll(){
        return this.orderService.getAll();
    }

    @PostMapping("/add")
    public void add(@RequestBody @Valid CreateOrderRequest createOrderRequest){
        this.orderService.add(createOrderRequest);
    }

    @PostMapping("/update")
    public void update(@RequestBody @Valid UpdateOrderRequest updateOrderRequest){
        this.orderService.update(updateOrderRequest);
    }

    @DeleteMapping("/delete/{orderId}")
    public void delete(@Valid @PathVariable int orderId){
        this.orderService.delete(orderId);
    }

    @GetMapping("/getbyid/{orderId}")
    public OrderListResponse getById(@PathVariable int orderId){
        return this.orderService.getById(orderId);
    }
}
