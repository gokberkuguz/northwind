package com.etiya.northwind.business.abstracts;

import com.etiya.northwind.business.requests.supplierRequests.CreateSupplierRequest;
import com.etiya.northwind.business.requests.supplierRequests.UpdateSupplierRequest;
import com.etiya.northwind.business.responses.suppliers.SupplierListResponse;
import java.util.List;

public interface SupplierService {
    void add(CreateSupplierRequest createSupplierRequest);
    void update(UpdateSupplierRequest updateSupplierRequest);
    void delete(int supplierId);
    List<SupplierListResponse> getAll();
    SupplierListResponse getById(int supplierId);
}
