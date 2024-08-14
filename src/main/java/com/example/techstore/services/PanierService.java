package com.example.techstore.services;

import java.util.List;
import com.example.techstore.dao.entites.Produit;

public interface PanierService {
    List<Produit> getPanierForUser(String userName);
    void addToPanier(String userName, Long produitId);
    void updatePanierItem(String userName, Long produitId, Integer quantity);
    void removeFromPanier(String userName, Long produitId);
}