package com.invest.ecommerceapi.infra.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.invest.ecommerceapi.infra.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findByEmail(String email);
}
