package com.rca.mysecurity.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LaptopDTO {
    @NotBlank(message = "Brand must not be null")
    private String brand;
    @NotBlank(message = "Sn must not be null")
    private String sn;

    private int studentId;
}
