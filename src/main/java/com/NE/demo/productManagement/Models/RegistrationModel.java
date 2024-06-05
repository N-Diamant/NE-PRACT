package com.NE.demo.productManagement.Models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

import lombok.Setter;

@Getter
@Setter
public class RegistrationModel {

    @NotNull
    private String firstname;

    @NotNull
    @Email
    private String email;
    @NotNull
    private String phone;

    @NotNull
//    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$")
//    @Size(min=8,max=32)
    private String password;



}