package com.etiya.northwind.business.concretes;

import com.etiya.northwind.business.abstracts.CustomerService;
import com.etiya.northwind.business.requests.customerRequests.CreateCustomerRequest;
import com.etiya.northwind.business.requests.customerRequests.UpdateCustomerRequest;
import com.etiya.northwind.business.responses.customers.CustomerListResponse;
import com.etiya.northwind.core.mapping.ModelMapperService;
import com.etiya.northwind.dataAccess.abstracts.CustomerRepository;
import com.etiya.northwind.entities.concretes.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerManager implements CustomerService {
    private CustomerRepository customerRepository;
    private ModelMapperService modelMapperService;

    @Autowired
    public CustomerManager(CustomerRepository customerRepository, ModelMapperService modelMapperService) {
        this.customerRepository = customerRepository;
        this.modelMapperService = modelMapperService;
    }


    @Override
    public void add(CreateCustomerRequest createCustomerRequest) {
        Customer customer = this.modelMapperService.forRequest().map(createCustomerRequest, Customer.class);
        customerRepository.save(customer);
    }

    @Override
    public void update(UpdateCustomerRequest updateCustomerRequest) {
        Customer customer = this.modelMapperService.forRequest().map(updateCustomerRequest, Customer.class);
        customerRepository.save(customer);
    }

    @Override
    public void delete(String customerId) {
        if(customerRepository.existsById(customerId)){
            customerRepository.deleteById(customerId);
        }
        else{
            System.out.println("Gecersiz Kullanici ID");
        }
    }

    @Override
    public List<CustomerListResponse> getAll() {
        List<Customer> result = this.customerRepository.findAll();
        List<CustomerListResponse> response =
                result.stream().map(customer -> this.modelMapperService.forResponse().map(customer, CustomerListResponse.class)).collect(Collectors.toList());

        return response;
    }

    @Override
    public CustomerListResponse getById(String customerId) {
        CustomerListResponse response = new CustomerListResponse();
        if (this.customerRepository.existsById(customerId)){
           Customer customer = this.customerRepository.findById(customerId).get();
           response = this.modelMapperService.forResponse().map(customer, CustomerListResponse.class);
        }
        else{
            System.out.println("Gecersiz Kullanici ID");
        }
        return response;
    }
}