package com.etiya.northwind.business.concretes;

import com.etiya.northwind.business.abstracts.EmployeeService;
import com.etiya.northwind.business.requests.employeeRequests.CreateEmployeeRequest;
import com.etiya.northwind.business.requests.employeeRequests.UpdateEmployeeRequest;
import com.etiya.northwind.business.responses.customers.CustomerListResponse;
import com.etiya.northwind.business.responses.employees.EmployeeListResponse;
import com.etiya.northwind.core.mapping.ModelMapperService;
import com.etiya.northwind.dataAccess.abstracts.EmployeeRepository;
import com.etiya.northwind.entities.concretes.Customer;
import com.etiya.northwind.entities.concretes.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class EmployeeManager implements EmployeeService {
    private EmployeeRepository employeeRepository;
    private ModelMapperService modelMapperService;

    @Autowired
    public EmployeeManager(EmployeeRepository employeeRepository, ModelMapperService modelMapperService) {
        this.employeeRepository = employeeRepository;
        this.modelMapperService = modelMapperService;
    }


    @Override
    public void add(CreateEmployeeRequest createEmployeeRequest) {
        Employee employee = this.modelMapperService.forRequest().map(createEmployeeRequest, Employee.class);
        employeeRepository.save(employee);
    }

    @Override
    public void update(UpdateEmployeeRequest updateEmployeeRequest) {
        Employee employee = this.modelMapperService.forRequest().map(updateEmployeeRequest, Employee.class);
        employeeRepository.save(employee);
    }

    @Override
    public void delete(int employeeId) {
        if(employeeRepository.existsById(employeeId)){
            employeeRepository.deleteById(employeeId);
        }
        else{
            System.out.println("Gecersiz Calisan ID");
        }
    }

    @Override
    public List<EmployeeListResponse> getAll() {
        List<Employee> result = this.employeeRepository.findAll();
        List<EmployeeListResponse> response =
                result.stream().map(employee -> this.modelMapperService.forResponse().map(employee, EmployeeListResponse.class)).collect(Collectors.toList());

        return response;
    }

    @Override
    public EmployeeListResponse getById(int employeeId) {
        EmployeeListResponse response = new EmployeeListResponse();
        if (this.employeeRepository.existsById(employeeId)){
            Employee employee = this.employeeRepository.findById(employeeId).get();
            response = this.modelMapperService.forResponse().map(employee, EmployeeListResponse.class);
        }
        else{
            System.out.println("Gecersiz Calisan ID");
        }
        return response;
    }
}
