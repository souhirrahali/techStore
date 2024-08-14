package com.example.techstore.services;

import org.springframework.stereotype.Service;
import com.example.techstore.dao.repositories.ProduitRepository;
import com.example.techstore.dao.repositories.UserRepository;
import jakarta.transaction.Transactional;
import com.example.techstore.dao.entites.Produit;
import com.example.techstore.dao.entites.User;
import java.util.List;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PanierServiceImpl implements PanierService {
    
    private final UserRepository userRepository;
    private final ProduitRepository produitRepository;

    @Override
    public List<Produit> getPanierForUser(String userName) {
        User user = userRepository.findByUserName(userName)
            .orElseThrow(() -> new RuntimeException("User not found"));
        return user.getPannier();
    }

    @Override
    @Transactional
    public void addToPanier(String userName, Long produitId) {
        User user = userRepository.findByUserName(userName)
            .orElseThrow(() -> new RuntimeException("User not found"));
        Produit produit = produitRepository.findById(produitId)
            .orElseThrow(() -> new RuntimeException("Product not found")); 
        if (!user.getPannier().contains(produit)) {
            user.getPannier().add(produit);
            userRepository.save(user);
        }
    }

    @Override
    @Transactional
    public void updatePanierItem(String userName, Long produitId, Integer quantity) {
        User user = userRepository.findByUserName(userName)
            .orElseThrow(() -> new RuntimeException("User not found"));
        Produit produit = user.getPannier().stream()
            .filter(p -> p.getId().equals(produitId))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Product not found in panier"));
        
        produit.setQuantity(quantity);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void removeFromPanier(String userName, Long produitId) {
        User user = userRepository.findByUserName(userName)
            .orElseThrow(() -> new RuntimeException("User not found"));
        user.getPannier().removeIf(p -> p.getId().equals(produitId));
        userRepository.save(user);
    }
}