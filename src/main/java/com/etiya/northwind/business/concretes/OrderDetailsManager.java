package com.etiya.northwind.business.concretes;
import com.etiya.northwind.business.abstracts.OrderDetailsService;
import com.etiya.northwind.business.requests.orderDetailRequests.CreateOrderDetailRequest;
import com.etiya.northwind.business.requests.orderDetailRequests.UpdateOrderDetailRequest;
import com.etiya.northwind.business.responses.orderDetails.OrderDetailsListResponse;
import com.etiya.northwind.core.mapping.ModelMapperService;
import com.etiya.northwind.dataAccess.abstracts.OrderDetailsRepository;
import com.etiya.northwind.entities.concretes.OrderDetails;
import com.etiya.northwind.entities.concretes.OrderDetailsId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderDetailsManager implements OrderDetailsService {
    private OrderDetailsRepository orderDetailsRepository;
    private ModelMapperService modelMapperService;

    @Autowired
    public OrderDetailsManager(OrderDetailsRepository orderDetailsRepository, ModelMapperService modelMapperService) {
        this.orderDetailsRepository = orderDetailsRepository;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public void add(CreateOrderDetailRequest createOrderDetailRequest) {
        OrderDetails orderDetails = this.modelMapperService.forRequest().map(createOrderDetailRequest, OrderDetails.class);
        orderDetailsRepository.save(orderDetails);
    }

    @Override
    public void update(UpdateOrderDetailRequest updateOrderDetailRequest) {
        OrderDetails orderDetails = this.modelMapperService.forRequest().map(updateOrderDetailRequest, OrderDetails.class);
        orderDetailsRepository.save(orderDetails);
    }

    @Override
    public void delete(OrderDetailsId orderDetailsId) {
        if(orderDetailsRepository.existsById(orderDetailsId)){
            orderDetailsRepository.deleteById(orderDetailsId);
        }
        else{
            System.out.println("Gecersiz Siparis Ayrintisi ID");
        }
    }

    @Override
    public List<OrderDetailsListResponse> getAll() {
        List<OrderDetails> result = this.orderDetailsRepository.findAll();
        List<OrderDetailsListResponse> response =
                result.stream().map(orderDetails -> this.modelMapperService.forResponse().map(orderDetails, OrderDetailsListResponse.class)).collect(Collectors.toList());

        return response;
    }

    @Override
    public OrderDetailsListResponse getById(OrderDetailsId orderDetailsId) {
        OrderDetailsListResponse response = new OrderDetailsListResponse();
        if (this.orderDetailsRepository.existsById(orderDetailsId)){
            OrderDetails orderDetails = this.orderDetailsRepository.findById(orderDetailsId).get();
            response = this.modelMapperService.forResponse().map(orderDetails, OrderDetailsListResponse.class);
        }
        else{
            System.out.println("Gecersiz Siparis Ayrintisi ID");
        }
        return response;
    }
}