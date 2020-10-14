package com.resvil.api.dao;

import com.resvil.api.classes.Stock;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockDao extends JpaRepository<Stock, Integer>
{

}
