package com.shopping.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopping.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{

	Optional<Role> findById(int id);

}
