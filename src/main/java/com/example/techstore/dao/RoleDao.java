package com.example.techstore.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.techstore.dao.entites.Role;

@Repository
public interface RoleDao extends CrudRepository<Role, String> {

}
