package com.example.techstore.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.techstore.dao.entites.Produit;
import com.example.techstore.dao.repositories.ProduitRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class ProduitServiceImpl implements ProduitService {
    
    private final ProduitRepository produitRepository;
    

    @Override
    public List<Produit> getAllProducts() {
        return produitRepository.findAll();
    }

    @Override
    public Optional<Produit> getProductById(Long id) {
        return produitRepository.findById(id);
    }

    @Override
    public Produit addProduct(Produit prod) {
        return produitRepository.save(prod);
    }

    @Override
    public void deleteProduct(Long id) {
        produitRepository.deleteById(id);
    }

    @Override
    public Optional<Produit> updateProduct(Produit prod) {
        return produitRepository.findById(prod.getId())
            .map(existingProd -> produitRepository.save(prod));
    }

  

}