package com.etiya.northwind.business.requests.categoryRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCategoryRequest {

    @NotNull
    @NotBlank
    @Positive
    private int categoryId;

    @NotNull
    @NotBlank
    private String categoryName;

    @NotNull
    @NotBlank
    private String description;
}
