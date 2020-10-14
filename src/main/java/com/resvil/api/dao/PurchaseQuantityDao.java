package com.resvil.api.dao;

import com.resvil.api.classes.PurchaseQuantity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseQuantityDao extends JpaRepository<PurchaseQuantity, Integer>
{
}
