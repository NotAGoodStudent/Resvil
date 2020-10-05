package com.resvil.api.dao;

import com.resvil.api.classes.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDao extends JpaRepository<Product, Integer>
{
}
