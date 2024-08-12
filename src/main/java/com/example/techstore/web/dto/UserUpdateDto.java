package com.example.techstore.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserUpdateDto {

    private Long id;
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Telephone number is required")
    private String numTelephone;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    @Pattern(regexp = ".+@.+\\..+", message = "Email should be in the format user@domain.com")
    private String email;

    @Size(min = 6, message = "Password should be at least 6 characters long")
    private String password;

}
