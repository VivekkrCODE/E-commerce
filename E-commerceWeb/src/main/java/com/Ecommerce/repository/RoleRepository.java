package com.Ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Ecommerce.model.Role;

import jakarta.persistence.criteria.CriteriaBuilder.In;

public interface RoleRepository extends JpaRepository<Role, Integer>{

}
