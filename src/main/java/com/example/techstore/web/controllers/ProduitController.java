package com.example.techstore.web.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.techstore.dao.entites.Produit;
import com.example.techstore.services.ProduitService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/produits")
@RequiredArgsConstructor
public class ProduitController {
    
    private final ProduitService produitService;
    private final String UPLOAD_DIR = "uploads/";

    @GetMapping
    public ResponseEntity<List<Produit>> getAllProducts() {
        return ResponseEntity.ok(produitService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produit> getProductById(@PathVariable Long id) {
        return produitService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Produit> addProduct(@RequestBody Produit produit) {
        return ResponseEntity.status(HttpStatus.CREATED).body(produitService.addProduct(produit));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produit> updateProduct(@PathVariable Long id, @RequestBody Produit produit) {
        produit.setId(id);
        return produitService.updateProduct(produit)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        produitService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/upload-image")
    public ResponseEntity<String> uploadImage(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        try {
            Produit produit = produitService.getProductById(id)
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(UPLOAD_DIR + fileName);
            Files.createDirectories(filePath.getParent());
            Files.write(filePath, file.getBytes());

            produit.setImagePath(fileName);
            produitService.updateProduct(produit);

            return ResponseEntity.ok("Image uploaded successfully");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Could not upload the image");
        }
    }
}