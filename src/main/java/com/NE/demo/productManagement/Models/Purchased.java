package com.NE.demo.productManagement.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@RequiredArgsConstructor
@Entity(name = "purchased")
public class Purchased {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer quantity;
    private Float total;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "productcode")
    private Product productCode;

    @ManyToOne
    @JoinColumn(name = "unit_price")
    private Product productPrice;

    //customer_id
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

}
