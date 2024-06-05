package com.NE.demo.productManagement.Models;

import jakarta.persistence.*;
import lombok.*;
import com.NE.demo.Enumerations.EUserRole;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Role {
    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    @Column (name = "role_id")
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_name")
    private EUserRole roleName;

    public Role(EUserRole roleName) {
        this.roleName = roleName;
    }
}
