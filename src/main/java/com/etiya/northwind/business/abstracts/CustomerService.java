package com.etiya.northwind.business.abstracts;

import com.etiya.northwind.business.requests.customerRequests.CreateCustomerRequest;
import com.etiya.northwind.business.requests.customerRequests.UpdateCustomerRequest;
import com.etiya.northwind.business.responses.PageDataResponse;
import com.etiya.northwind.business.responses.customers.CustomerListResponse;



import java.util.List;

public interface CustomerService {
    void add(CreateCustomerRequest createCustomerRequest);
    void update(UpdateCustomerRequest updateCustomerRequest);
    void delete(String customerId);
    List<CustomerListResponse> getAll();
    CustomerListResponse getById(String customerId);

    PageDataResponse<CustomerListResponse> getByPage(int pageNumber, int customerAmountInPage);

    PageDataResponse<CustomerListResponse> getByPageWithSorting(int pageNumber, int customerAmountInPage, String fieldName, boolean isAsc);
}
