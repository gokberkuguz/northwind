package com.etiya.northwind.business.concretes;

import com.etiya.northwind.business.abstracts.SupplierService;
import com.etiya.northwind.business.requests.supplierRequests.CreateSupplierRequest;
import com.etiya.northwind.business.requests.supplierRequests.UpdateSupplierRequest;
import com.etiya.northwind.business.responses.suppliers.SupplierListResponse;
import com.etiya.northwind.core.mapping.ModelMapperService;
import com.etiya.northwind.dataAccess.abstracts.SupplierRepository;
import com.etiya.northwind.entities.concretes.Product;
import com.etiya.northwind.entities.concretes.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierManager implements SupplierService {
    private SupplierRepository supplierRepository;
    private ModelMapperService modelMapperService;

    @Autowired
    public SupplierManager(SupplierRepository supplierRepository, ModelMapperService modelMapperService) {
        this.supplierRepository = supplierRepository;
        this.modelMapperService = modelMapperService;
    }


    @Override
    public void add(CreateSupplierRequest createSupplierRequest) {
        Supplier supplier = this.modelMapperService.forRequest().map(createSupplierRequest, Supplier.class);
        supplierRepository.save(supplier);
    }

    @Override
    public void update(UpdateSupplierRequest updateSupplierRequest) {
        Supplier supplier = this.modelMapperService.forRequest().map(updateSupplierRequest, Supplier.class);
        supplierRepository.save(supplier);
    }

    @Override
    public void delete(int supplierId) {
        if (this.supplierRepository.existsById(supplierId)){
            this.supplierRepository.deleteById(supplierId);
        }else{
            System.out.println("Gecersiz Tedarikçi ID");
        }
    }

    @Override
    public List<SupplierListResponse> getAll() {
        List<Supplier> result = this.supplierRepository.findAll();
        List<SupplierListResponse> response = result.stream()
                .map(supplier -> this.modelMapperService.forResponse().map(supplier, SupplierListResponse.class))
                .collect(Collectors.toList());

        return response;
    }

    @Override
    public SupplierListResponse getById(int supplierId) {
        SupplierListResponse response = new SupplierListResponse();
        if (this.supplierRepository.existsById(supplierId)){
            Supplier supplier = this.supplierRepository.findById(supplierId).get();
            response = this.modelMapperService.forResponse().map(supplier, SupplierListResponse.class);
        }
        else{
            System.out.println("Gecersiz Tedarikçi ID");
        }
        return response;
    }
}

