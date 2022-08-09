package com.etiya.northwind.business.concretes;

import com.etiya.northwind.business.abstracts.OrderService;
import com.etiya.northwind.business.requests.orderRequests.CreateOrderRequest;
import com.etiya.northwind.business.requests.orderRequests.UpdateOrderRequest;
import com.etiya.northwind.business.responses.orders.OrderListResponse;
import com.etiya.northwind.core.mapping.ModelMapperService;
import com.etiya.northwind.dataAccess.abstracts.OrderRepository;
import com.etiya.northwind.entities.concretes.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderManager implements OrderService {
    private OrderRepository orderRepository;
    private ModelMapperService modelMapperService;

    @Autowired
    public OrderManager(OrderRepository orderRepository, ModelMapperService modelMapperService) {
        this.orderRepository = orderRepository;
        this.modelMapperService = modelMapperService;
    }


    @Override
    public void add(CreateOrderRequest createOrderRequest) {
        Order order = this.modelMapperService.forRequest().map(createOrderRequest, Order.class);
        orderRepository.save(order);
    }

    @Override
    public void update(UpdateOrderRequest updateOrderRequest) {
        Order order = this.modelMapperService.forRequest().map(updateOrderRequest, Order.class);
        orderRepository.save(order);
    }

    @Override
    public void delete(int orderId) {
        if(orderRepository.existsById(orderId)){
            orderRepository.deleteById(orderId);
        }
        else{
            System.out.println("Gecersiz Siparis ID");
        }
    }

    @Override
    public List<OrderListResponse> getAll() {
        List<Order> result = this.orderRepository.findAll();
        List<OrderListResponse> response =
                result.stream().map(order -> this.modelMapperService.forResponse().map(order, OrderListResponse.class)).collect(Collectors.toList());

        for (int i = 0;i < result.size(); i++){
            response.get(i).setEmployeeName(result.get(i).getEmployee().getFirstName()+" "+result.get(i).getEmployee().getLastName());
        }
        return response;
    }

    @Override
    public OrderListResponse getById(int orderId) {
        OrderListResponse response = new OrderListResponse();
        if (this.orderRepository.existsById(orderId)){
            Order order = this.orderRepository.findById(orderId).get();
            response = this.modelMapperService.forResponse().map(order, OrderListResponse.class);
            response.setEmployeeName(order.getEmployee().getFirstName()+" "+order.getEmployee().getLastName());
        }
        else{
            System.out.println("Gecersiz Siparis ID");
        }
        return response;
    }
}

