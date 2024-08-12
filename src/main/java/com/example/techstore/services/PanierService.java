package com.example.techstore.services;

import java.util.List;

import com.example.techstore.dao.entites.Produit;

public interface PanierService {
    List<Produit> getPanierForUser(Long userId);
    void addToPanier(Long userId, Long produitId);
    void updatePanierItem(Long userId, Long produitId, Integer quantity);
    void removeFromPanier(Long userId, Long produitId);
}