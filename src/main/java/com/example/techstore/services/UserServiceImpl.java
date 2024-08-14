/* package com.example.techstore.services;

import com.example.techstore.dao.entites.User;
import com.example.techstore.dao.enums.Role;
import com.example.techstore.dao.entites.Produit;
import com.example.techstore.dao.repositories.UserRepository;
import com.example.techstore.dao.repositories.ProduitRepository;
import com.example.techstore.web.dto.UserRegistrationDto;
import com.example.techstore.web.dto.UserLoginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ProduitRepository produitRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean isEmailTaken(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    @Override
    public User registerUser(UserRegistrationDto registrationDto) {
        if (isEmailTaken(registrationDto.getEmail())) {
            throw new RuntimeException("Email is already taken");
        }

        User user = User.builder()
                .name(registrationDto.getFirstName() + " " + registrationDto.getLastName())
                .email(registrationDto.getEmail())
                .password(passwordEncoder.encode(registrationDto.getPassword()))
                .numTelephone(registrationDto.getNumTelephone())
                .role(Role.USER)  // Assuming all registered users are assigned the USER role by default
                .build();
        
        return userRepository.save(user);
    }

    @Override
    public String loginUser(UserLoginDto loginDto) {
        Optional<User> userOptional = userRepository.findByEmail(loginDto.getEmail());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
                // Generate a token or perform login logic
                return "DummyToken"; // Replace with actual token generation logic
            }
        }
        return null;
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<Produit> getUserPanier(Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found"));
        return user.getPannier();
    }

    @Override
    @Transactional
    public void addProductToPanier(Long userId, Long produitId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));
        Produit produit = produitRepository.findById(produitId)
            .orElseThrow(() -> new RuntimeException("Product not found"));
        
        user.getPannier().add(produit);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void removeProductFromPanier(Long userId, Long produitId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));
        user.getPannier().removeIf(p -> p.getId().equals(produitId));
        userRepository.save(user);
    }
}
 */