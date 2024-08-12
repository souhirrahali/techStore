package com.example.techstore.services;

import java.util.List;
import java.util.Optional;

import com.example.techstore.dao.entites.Produit;


public interface ProduitService {
    List<Produit> getAllProducts();
    Optional<Produit> getProductById(Long id);
    Produit addProduct(Produit prod);
    void deleteProduct(Long id);
    Optional<Produit> updateProduct(Produit prod);
}
