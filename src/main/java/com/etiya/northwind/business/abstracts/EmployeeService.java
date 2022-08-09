package com.etiya.northwind.business.abstracts;

import com.etiya.northwind.business.requests.employeeRequests.CreateEmployeeRequest;
import com.etiya.northwind.business.requests.employeeRequests.UpdateEmployeeRequest;
import com.etiya.northwind.business.responses.PageDataResponse;
import com.etiya.northwind.business.responses.employees.EmployeeListResponse;


import java.util.List;

public interface EmployeeService {

    void add(CreateEmployeeRequest createEmployeeRequest);
    void update(UpdateEmployeeRequest updateEmployeeRequest);
    void delete(int employeeId);
    List<EmployeeListResponse> getAll();
    EmployeeListResponse getById(int employeeId);

    PageDataResponse<EmployeeListResponse> getByPage(int pageNumber, int employeeAmountInPage);

    PageDataResponse<EmployeeListResponse> getByPageWithSorting(int pageNumber, int employeeAmountInPage, String fieldName, boolean isAsc);

}
