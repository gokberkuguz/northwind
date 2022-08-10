package com.etiya.northwind.business.requests.orderRequests;

import com.etiya.northwind.business.requests.orderDetailRequests.CreateOrderDetailRequest;
import com.etiya.northwind.entities.concretes.OrderDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderRequest {

    @NotNull
    @Positive
    private int orderId;

    @NotNull
    @NotBlank
    private String customerId;

    @NotNull
    @Positive
    private int employeeId;

    @NotNull
    private LocalDate orderDate;

    @NotNull
    private List<CreateOrderDetailRequest> orderDetailRequests;
}
