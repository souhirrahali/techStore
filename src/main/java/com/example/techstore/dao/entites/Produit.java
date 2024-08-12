package com.example.techstore.dao.entites;


import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Entity
@Data
public class Produit  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "produit_id", unique = true, nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "categorie")
    private String categorie;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "price")
    private Double price;

    @Column(name = "quantity")
    private Integer quantity;

    private String imagePath;
    
    @ManyToMany(mappedBy = "pannier")
     @JsonIgnore
    private List<User> users;
}
