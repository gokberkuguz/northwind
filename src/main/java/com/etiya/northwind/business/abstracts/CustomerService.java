package com.etiya.northwind.business.abstracts;

import com.etiya.northwind.business.requests.customerRequests.CreateCustomerRequest;
import com.etiya.northwind.business.requests.customerRequests.UpdateCustomerRequest;
import com.etiya.northwind.business.requests.employeeRequests.CreateEmployeeRequest;
import com.etiya.northwind.business.requests.employeeRequests.UpdateEmployeeRequest;
import com.etiya.northwind.business.responses.customers.CustomerListResponse;


import java.util.List;

public interface CustomerService {
    void add(CreateCustomerRequest createCustomerRequest);
    void update(UpdateCustomerRequest updateCustomerRequest);
    void delete(String customerId);
    List<CustomerListResponse> getAll();
    CustomerListResponse getById(String customerId);
}
