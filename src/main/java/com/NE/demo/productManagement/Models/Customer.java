package com.NE.demo.productManagement.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.NE.demo.Enumerations.EGender;

@Getter
@Setter
@Entity(name = "customer")
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private Long telephone;
    private String email;
    private String password;


    @OneToMany(mappedBy = "customer")
    private List<Purchased> purchases;


    public Customer() {
    }

    public Customer(Long id, String firstName, Long telephone, String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.telephone = telephone;
        this.email = email;
        this.password = password;

    }




    public Collection<Object> getRoles() {
        return null;
    }
}
