package com.example.userManagement.repositry;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.userManagement.model.User;


public interface UserRepo extends JpaRepository<User, Integer>
{
User findByEmail(String email);
boolean existsByEmail(String email);

}
