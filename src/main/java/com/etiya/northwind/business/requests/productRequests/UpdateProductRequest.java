package com.etiya.northwind.business.requests.productRequests;

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
public class UpdateProductRequest {

    @NotNull
    @NotBlank
    @Positive
    private int productId;

    @NotNull
    @NotBlank
    private String productName;

    @NotNull
    @NotBlank
    @PositiveOrZero
    private double unitPrice;

    @NotNull
    @NotBlank
    @PositiveOrZero
    private int unitsInStock;

    @NotNull
    @NotBlank
    @Positive
    private int categoryId;

    @NotNull
    @NotBlank
    @PositiveOrZero
    private int discontinued;
}
