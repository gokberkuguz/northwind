package com.etiya.northwind.api.controllers;

import com.etiya.northwind.business.abstracts.CustomerService;
import com.etiya.northwind.business.requests.customerRequests.CreateCustomerRequest;
import com.etiya.northwind.business.requests.customerRequests.UpdateCustomerRequest;
import com.etiya.northwind.business.responses.categories.CategoryListResponse;
import com.etiya.northwind.business.responses.customers.CustomerListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomersController {
    private CustomerService customerService;

    @Autowired
    public CustomersController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/getall")
    public List<CustomerListResponse> getAll(){
        return this.customerService.getAll();
    }

    @PostMapping("/add")
    public void add(@RequestBody @Valid CreateCustomerRequest createCustomerRequest){
        this.customerService.add(createCustomerRequest);
    }

    @PostMapping("/update")
    public void update(@RequestBody @Valid UpdateCustomerRequest updateCustomerRequest){
        this.customerService.update(updateCustomerRequest);
    }

    @DeleteMapping("/delete/{customerId}")
    public void delete(@Valid @PathVariable String customerId){
        this.customerService.delete(customerId);
    }

    @GetMapping("/getbyid/{customerId}")
    public CustomerListResponse getById(@PathVariable String customerId){
        return this.customerService.getById(customerId);
    }


}
