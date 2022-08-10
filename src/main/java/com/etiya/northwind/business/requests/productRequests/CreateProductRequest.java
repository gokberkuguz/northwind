package com.etiya.northwind.business.requests.productRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductRequest {

    @NotBlank
    @NotNull
    @Size(min = 1, max=10)
    private int productId;

    @NotNull
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
