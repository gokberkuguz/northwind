package com.etiya.northwind.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Table(name="order_details")
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@IdClass(OrderDetailsId.class)
public class OrderDetails{

    @Id
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Id
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name="unit_price")
    private double unitPrice;

    @Column(name="quantity")
    private int quantity;

    @Column(name="discount")
    private double discount;
}
