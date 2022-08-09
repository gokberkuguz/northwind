package com.etiya.northwind.business.abstracts;

import com.etiya.northwind.business.requests.orderDetailRequests.CreateOrderDetailRequest;
import com.etiya.northwind.business.requests.orderDetailRequests.UpdateOrderDetailRequest;
import com.etiya.northwind.business.responses.orderDetails.OrderDetailsListResponse;
import com.etiya.northwind.entities.concretes.OrderDetailsId;

import java.util.List;

public interface OrderDetailsService {
    void add(CreateOrderDetailRequest createOrderDetailRequest);
    void update(UpdateOrderDetailRequest updateOrderDetailRequest);
    void delete(OrderDetailsId orderDetailsId);
    List<OrderDetailsListResponse> getAll();
    OrderDetailsListResponse getById(OrderDetailsId orderDetailsId);

}
