package com.NE.demo.productManagement.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity(name = "quantity")
public class Quantity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int product_code;
    private int quantity;
    private String operation;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "product_quantity")
    private Product product;


    public Quantity() {
    }

    public Quantity(Long id, int product_code, int quantity, String operation, LocalDate date) {
        this.id = id;
        this.product_code = product_code;
        this.quantity = quantity;
        this.operation = operation;
        this.date = date;
    }


}
