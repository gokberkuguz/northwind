package com.etiya.northwind.business.requests.orderDetailRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateOrderDetailRequest {

    @NotNull
    @NotBlank
    @Positive
    private int productId;

    @NotNull
    @NotBlank
    @Positive
    private int employeeId;

    @NotNull
    @NotBlank
    @Positive
    private int orderId;

    @NotNull
    @NotBlank
    @PositiveOrZero
    private double unitPrice;

    @NotNull
    @NotBlank
    @PositiveOrZero
    private int quantity;

    @NotNull
    @NotBlank
    @PositiveOrZero
    private double discount;
}
