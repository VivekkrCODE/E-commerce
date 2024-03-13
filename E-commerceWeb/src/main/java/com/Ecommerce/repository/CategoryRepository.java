package com.Ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Ecommerce.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
