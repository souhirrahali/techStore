package com.example.techstore.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.techstore.dao.entites.Produit;

import java.util.List;
import java.time.LocalDate;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {
    
    List<Produit> findByCategorie(String categorie);
    List<Produit> findByNameContainingIgnoreCase(String name);
    List<Produit> findByPriceBetween(Double minPrice, Double maxPrice);
    List<Produit> findByDateAfter(LocalDate date);
    List<Produit> findByQuantityGreaterThan(Integer minQuantity);
    @Query("SELECT p FROM Produit p WHERE p.categorie = :categorie AND p.price BETWEEN :minPrice AND :maxPrice")
    List<Produit> findByCategorieAndPriceRange(
        @Param("categorie") String categorie,
        @Param("minPrice") Double minPrice,
        @Param("maxPrice") Double maxPrice
    );
    @Query("SELECT p.categorie, COUNT(p) FROM Produit p GROUP BY p.categorie")
    List<Object[]> countProductsByCategorie();
}