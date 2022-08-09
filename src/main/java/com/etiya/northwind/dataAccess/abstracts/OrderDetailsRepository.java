package com.etiya.northwind.dataAccess.abstracts;

import com.etiya.northwind.entities.concretes.OrderDetails;
import com.etiya.northwind.entities.concretes.OrderDetailsId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, OrderDetailsId> {

}
