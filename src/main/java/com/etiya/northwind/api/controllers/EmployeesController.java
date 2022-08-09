package com.etiya.northwind.api.controllers;

import com.etiya.northwind.business.abstracts.EmployeeService;
import com.etiya.northwind.business.requests.employeeRequests.CreateEmployeeRequest;
import com.etiya.northwind.business.requests.employeeRequests.UpdateEmployeeRequest;
import com.etiya.northwind.business.responses.PageDataResponse;
import com.etiya.northwind.business.responses.employees.EmployeeListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeesController {
    private EmployeeService employeeService;

    @Autowired
    public EmployeesController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/getall")
    public List<EmployeeListResponse> getAll(){
        return this.employeeService.getAll();
    }

    @PostMapping("/add")
    public void add(@RequestBody @Valid CreateEmployeeRequest createEmployeeRequest){
        this.employeeService.add(createEmployeeRequest);
    }

    @PostMapping("/update")
    public void update(@RequestBody @Valid UpdateEmployeeRequest updateEmployeeRequest){
        this.employeeService.update(updateEmployeeRequest);
    }

    @DeleteMapping("/delete/{employeeId}")
    public void delete(int employeeId){
        this.employeeService.delete(employeeId);
    }

    @GetMapping("/getbyid/{employeeId}")
    public EmployeeListResponse getById(int employeeId){
        return this.employeeService.getById(employeeId);
    }

    @GetMapping("/getByPage/{pageNumber}/{employeeAmountInPage}")
    public PageDataResponse<EmployeeListResponse> getByPage(int pageNumber, int employeeAmountInPage){
        return this.employeeService.getByPage(pageNumber,employeeAmountInPage);
    }

    @GetMapping("/getByPageWithSorting/{pageNumber}/{employeeAmountInPage}/{fieldName}/{isAsc}")
    public PageDataResponse<EmployeeListResponse> getByPageWithSorting(int pageNumber, int employeeAmountInPage, String fieldName, boolean isAsc){
        return this.employeeService.getByPageWithSorting(pageNumber,employeeAmountInPage,fieldName,isAsc);
    }
}
