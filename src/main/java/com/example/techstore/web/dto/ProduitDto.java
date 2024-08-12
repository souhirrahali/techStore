package com.example.techstore.web.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ProduitDto {
        private Long id;
    
    private String name;

    private String description;

    private String categorie;

    private LocalDate date;

    private Double price;

    private Integer quantity;
}