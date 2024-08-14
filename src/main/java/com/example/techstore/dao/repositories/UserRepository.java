package com.example.techstore.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.techstore.dao.entites.User;
import com.example.techstore.dao.entites.Role;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByUserEmail(String email);
    List<User> findByRole(Role role);
    List<User> findByUserNameContainingIgnoreCase(String userName);
    boolean existsByUserEmail(String email);

    @Query("SELECT u.role, COUNT(u) FROM User u GROUP BY u.role")
    List<Object[]> countUsersByRole();

    Optional<User> findByUserName(String userName);
}