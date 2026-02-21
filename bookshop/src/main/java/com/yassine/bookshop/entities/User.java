package com.yassine.bookshop.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "Users", schema = "bookshop", uniqueConstraints = {@UniqueConstraint(name = "email",
        columnNames = {"email"})})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 255)
    @NotNull
    @Column(name = "email", nullable = false)
    private String email;

    @Size(max = 255)
    @NotNull
    @Column(name = "passwordHash", nullable = false)
    private String passwordHash;

    @Size(max = 50)
    @ColumnDefault("'customer'")
    @Column(name = "role", length = 50)
    private String role;


}