package com.example.techstore.web.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.techstore.dao.entites.Produit;
import com.example.techstore.services.PanierService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/panier")
@RequiredArgsConstructor
public class PanierController {
    
    private final PanierService panierService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<Produit>> getPanier(@PathVariable String userId) {
        return ResponseEntity.ok(panierService.getPanierForUser(userId));
    }

    @PostMapping("/{userId}/add/{produitId}")
    public ResponseEntity<Void> addToPanier(@PathVariable String userId, @PathVariable Long produitId) {
        panierService.addToPanier(userId, produitId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{userId}/update/{produitId}")
    public ResponseEntity<Void> updatePanierItem(@PathVariable String userId, @PathVariable Long produitId, @RequestBody Integer quantity) {
        panierService.updatePanierItem(userId, produitId, quantity);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{userId}/remove/{produitId}")
    public ResponseEntity<Void> removeFromPanier(@PathVariable String userId, @PathVariable Long produitId) {
        panierService.removeFromPanier(userId, produitId);
        return ResponseEntity.ok().build();
    }
}