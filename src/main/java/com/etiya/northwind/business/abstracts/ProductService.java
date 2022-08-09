package com.etiya.northwind.business.abstracts;

import com.etiya.northwind.business.requests.productRequests.CreateProductRequest;
import com.etiya.northwind.business.requests.productRequests.UpdateProductRequest;
import com.etiya.northwind.business.responses.PageDataResponse;
import com.etiya.northwind.business.responses.orders.OrderListResponse;
import com.etiya.northwind.business.responses.products.ProductListResponse;

import java.util.List;

public interface ProductService {
    void add(CreateProductRequest createProductRequest);
    void update(UpdateProductRequest updateProductRequest);
    void delete(int productId);
    List<ProductListResponse> getAll();
    ProductListResponse getById(int productId);

    PageDataResponse<ProductListResponse> getByPage(int pageNumber, int orderAmountInPage);

    PageDataResponse<ProductListResponse> getByPageWithSorting(int pageNumber, int orderAmountInPage, String fieldName, boolean isAsc);
}
