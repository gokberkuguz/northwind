package com.etiya.northwind.entities.concretes;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderDetailsId implements Serializable {

    private Order order;

    private Product product;
}
