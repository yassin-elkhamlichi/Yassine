package com.yassine.bookshop.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AuthUserDto {
    @Email
    String email;
    @Size(min = 8, message = "Password must be at least 8 characters")
    String passwordHash;
}
