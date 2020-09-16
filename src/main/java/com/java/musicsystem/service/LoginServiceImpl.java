package com.java.musicsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.musicsystem.dao.LoginRepository;
import com.java.musicsystem.entity.Login;

@Service
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private LoginRepository loginRepository;
	
	@Override
	public void save(Login theLogin) {
		loginRepository.save(theLogin);
	}

	@Override
	public List<Login> getAll() {
		return loginRepository.findAll();
	}
}

