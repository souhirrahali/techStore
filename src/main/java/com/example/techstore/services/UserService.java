package com.example.techstore.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.techstore.dao.RoleDao;
import com.example.techstore.dao.UserDao;
import com.example.techstore.dao.entites.Role;
import com.example.techstore.dao.entites.User;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void initRoleAndUser() {

        Role adminRole = new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Admin role");
        roleDao.save(adminRole);

        Role userRole = new Role();
        userRole.setRoleName("User");
        userRole.setRoleDescription("Default role for newly created record");
        roleDao.save(userRole);

        User adminUser = new User();
        adminUser.setUserName("admin123");
        adminUser.setUserPassword(getEncodedPassword("admin@pass"));
        adminUser.setUserFirstName("admin");
        adminUser.setUserLastName("admin");
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRole(adminRoles);
        userDao.save(adminUser);

//        User user = new User();
//        user.setUserName("raj123");
//        user.setUserPassword(getEncodedPassword("raj@123"));
//        user.setUserFirstName("raj");
//        user.setUserLastName("sharma");
//        Set<Role> userRoles = new HashSet<>();
//        userRoles.add(userRole);
//        user.setRole(userRoles);
//        userDao.save(user);
    }

    public User registerNewUser(User user) {
        Role role = roleDao.findById("User").get();
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);
        user.setRole(userRoles);
        user.setUserPassword(getEncodedPassword(user.getUserPassword()));

        return userDao.save(user);
    }

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
}

/* 
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
} */
