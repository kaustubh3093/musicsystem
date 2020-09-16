package com.java.musicsystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.musicsystem.entity.Login;

public interface LoginRepository extends JpaRepository<Login, String>{

}
