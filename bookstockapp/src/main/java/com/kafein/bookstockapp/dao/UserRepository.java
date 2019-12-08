package com.kafein.bookstockapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kafein.bookstockapp.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);

}
