package com.example.techstore.web.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRegistrationDto {
    private String firstName;
    private String lastName;
    private String email;
    private String numTelephone;
    private String password;
    private String confirmPassword;
   
}
