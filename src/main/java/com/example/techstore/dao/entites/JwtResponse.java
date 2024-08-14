package com.example.techstore.dao.entites;

import lombok.Data;


@Data
public class JwtResponse {

    private User user;
    private String jwtToken;
    
    public JwtResponse(User user, String jwtToken) {
        this.user = user;
        this.jwtToken = jwtToken;
    }
}