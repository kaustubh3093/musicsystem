package com.java.musicsystem.service;

import java.util.List;

import com.java.musicsystem.entity.Login;

public interface LoginService {

	void save(Login theLogin);
	List<Login> getAll();
}
