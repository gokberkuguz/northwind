package com.etiya.northwind.business.abstracts;

import com.etiya.northwind.business.requests.productRequests.CreateProductRequest;
import com.etiya.northwind.business.requests.productRequests.UpdateProductRequest;
import com.etiya.northwind.business.responses.products.ProductListResponse;

import java.util.List;

public interface ProductService {
    void add(CreateProductRequest createProductRequest);
    void update(UpdateProductRequest updateProductRequest);
    void delete(int productId);
    List<ProductListResponse> getAll();
    ProductListResponse getById(int productId);
}
