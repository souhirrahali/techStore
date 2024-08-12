package com.example.techstore.dao.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.techstore.dao.entites.User;
import com.example.techstore.dao.entites.Produit;
import com.example.techstore.dao.enums.Role;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    List<User> findByRole(Role role);
    List<User> findByNameContainingIgnoreCase(String name);
    boolean existsByEmail(String email);

    @Query("SELECT u.pannier FROM User u WHERE u.id = :userId")
    List<Produit> findPannierByUserId(@Param("userId") Long userId);

    @Modifying
    @Query("UPDATE User u SET u.pannier = :produit WHERE u.id = :userId")
    void addToPanier(@Param("userId") Long userId, @Param("produit") Produit produit);

    @Modifying
    @Query("DELETE FROM User u WHERE u.id = :userId AND :produitId IN (SELECT p.id FROM u.pannier p WHERE p.id = :produitId)")
    void removeFromPanier(@Param("userId") Long userId, @Param("produitId") Long produitId);
    

    @Query("SELECT u.role, COUNT(u) FROM User u GROUP BY u.role")
    List<Object[]> countUsersByRole();
}