package com.resvil.api.dao;

import com.resvil.api.classes.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, String>
{
}

