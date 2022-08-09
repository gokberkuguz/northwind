package com.etiya.northwind.business.abstracts;

import com.etiya.northwind.business.requests.orderRequests.CreateOrderRequest;
import com.etiya.northwind.business.requests.orderRequests.UpdateOrderRequest;
import com.etiya.northwind.business.responses.PageDataResponse;
import com.etiya.northwind.business.responses.orders.OrderListResponse;
import java.util.List;

public interface OrderService {
    void add(CreateOrderRequest createOrderRequest);
    void update(UpdateOrderRequest updateOrderRequest);
    void delete(int orderId);
    List<OrderListResponse> getAll();
    OrderListResponse getById(int orderId);

    PageDataResponse<OrderListResponse> getByPage(int pageNumber, int orderAmountInPage);

    PageDataResponse<OrderListResponse> getByPageWithSorting(int pageNumber, int orderAmountInPage, String fieldName, boolean isAsc);
}
