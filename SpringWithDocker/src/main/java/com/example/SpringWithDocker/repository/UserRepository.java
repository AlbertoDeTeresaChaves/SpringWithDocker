package com.example.SpringWithDocker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.SpringWithDocker.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
