package com.example.techstore.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.techstore.dao.entites.User;

@Repository
public interface UserDao extends CrudRepository<User, String> {
}
