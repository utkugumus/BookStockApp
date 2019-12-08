package com.kafein.bookstockapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kafein.bookstockapp.model.Role;

@Repository
public interface RoleRepository extends  JpaRepository<Role, Integer>{
	
}
