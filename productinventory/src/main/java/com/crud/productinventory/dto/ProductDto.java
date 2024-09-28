package com.crud.productinventory.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ProductDto {

    @NotEmpty(message = "Product name can not be a null or empty")
    private String name;

    @NotEmpty(message = "Product description can not be a null or empty")
    private String description;

    @NotEmpty(message = "Product type can not be a null or empty")
    private String type;

    @NotNull(message = "Product quantity cannot be empty")
    private int quantity;

    @Positive(message = "Product price cannot be zero or negative")
    private double unitPrice;
}
