package com.etiya.northwind.business.requests.orderRequests;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateOrderRequest {

    @NotNull
    @NotBlank
    @Positive
    private int orderId;

    @NotNull
    @NotBlank
    private String customerId;

    @NotNull
    @NotBlank
    @Positive
    private int employeeId;

    @NotNull
    @NotBlank
    private LocalDate orderDate;
}
