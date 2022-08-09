package com.etiya.northwind.api.controllers;

import com.etiya.northwind.business.abstracts.ProductService;
import com.etiya.northwind.business.abstracts.SupplierService;
import com.etiya.northwind.business.requests.supplierRequests.CreateSupplierRequest;
import com.etiya.northwind.business.requests.supplierRequests.UpdateSupplierRequest;
import com.etiya.northwind.business.responses.suppliers.SupplierListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
public class SuppliersController {
    private SupplierService supplierService;

    @Autowired
    public SuppliersController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping("/getall")
    public List<SupplierListResponse> getAll(){
        return this.supplierService.getAll();
    }

    @PostMapping("/add")
    public void add(@RequestBody @Valid CreateSupplierRequest createSupplierRequest){
        this.supplierService.add(createSupplierRequest);
    }

    @PostMapping("/update")
    public void update(@RequestBody @Valid UpdateSupplierRequest updateSupplierRequest){
        this.supplierService.update(updateSupplierRequest);
    }

    @DeleteMapping("/delete/{supplierId}")
    public void delete(@Valid @PathVariable int supplierId){
        this.supplierService.delete(supplierId);
    }

    @GetMapping("/getbyid/{supplierId}")
    public SupplierListResponse getById(@PathVariable int supplierId){
        return this.supplierService.getById(supplierId);
    }
}
