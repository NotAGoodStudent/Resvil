package com.resvil.api.dao;

import com.resvil.api.classes.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleDao extends JpaRepository<Sale, Integer>
{

}
