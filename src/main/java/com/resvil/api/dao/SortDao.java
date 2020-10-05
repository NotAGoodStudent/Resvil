package com.resvil.api.dao;

import com.resvil.api.classes.Sale;
import com.resvil.api.classes.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SortDao extends JpaRepository<Stock, Integer>
{

}
