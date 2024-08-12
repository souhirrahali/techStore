package com.example.techstore.services;

import com.example.techstore.dao.entites.User;
import com.example.techstore.dao.entites.Produit;
import com.example.techstore.web.dto.UserRegistrationDto;
import com.example.techstore.web.dto.UserLoginDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    boolean isEmailTaken(String email);
    User registerUser(UserRegistrationDto registrationDto);
    String loginUser(UserLoginDto loginDto);
    Optional<User> getUserById(Long id);
    User updateUser(User user);
    void deleteUser(Long id);
    List<Produit> getUserPanier(Long id);
    void addProductToPanier(Long userId, Long produitId);
    void removeProductFromPanier(Long userId, Long produitId);
}