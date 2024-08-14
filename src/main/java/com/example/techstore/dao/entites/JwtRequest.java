package com.example.techstore.dao.entites;

import lombok.Data;

@Data
public class JwtRequest {

    private String userName;
    private String userPassword;
}