package com.java.musicsystem.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.java.musicsystem.dao.LoginRepository;
import com.java.musicsystem.entity.Login;

@Service
public class LoginServiceImpl implements LoginService {
	
	private static final Logger LOG = LoggerFactory.getLogger(LoginServiceImpl.class);
	
	@Autowired
	private LoginRepository loginRepository;
	
	@Override
	public void save(Login theLogin) {
		LOG.debug("Creating record in the login database for [{}]", theLogin);
		
		loginRepository.save(theLogin);
	}

	@Override
	public List<Login> getAll() {
		LOG.debug("Get all the record from the login database");
		
		return loginRepository.findAll();
	}
}

