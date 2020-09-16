package com.java.musicsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.java.musicsystem.entity.Credentials;
import com.java.musicsystem.entity.Login;
import com.java.musicsystem.service.LoginService;

@Controller
@RequestMapping("/api")
public class MusicController {

	@Autowired
	private LoginService loginService;
	
	public static String username;
	
	@RequestMapping("/main")
	public String showMainPage() {
		
		return "main";
	}
	
	
	@PostMapping("/save")
	public String saveLogin(@ModelAttribute("login") Login theLogin) {
		List<Login> list = loginService.getAll();
		for(Login login: list) {
			if(login.getUsername().equals(theLogin.getUsername())) {
				return "distinctUsername";
			}
		}
		loginService.save(theLogin);
		
		return "redirect:/api/main";
	}
	
	
	@PostMapping("/generateInfo")
	public String generateInfo(@ModelAttribute("credential") Credentials theCredentials) {
		
		username = theCredentials.getUsername();
		List<Login> list = loginService.getAll();
		boolean validCredentials = false;
		boolean admin = false;
		for(Login login: list) {
			if(login.getUsername().equals(theCredentials.getUsername()) && login.getPassword().equals(theCredentials.getPassword())) {
				validCredentials = true;
				if(login.getRole().equals("Admin"))
					admin = true;
				break;
			}
					
		}
		if(validCredentials == false) {
			return "error";
		}
		
		if(admin == false) {
			return "normaluser";
		}
		
		return "admin";
	}
	
	@GetMapping("/showFormToRegister")
	public String showFormToRegister(Model theModel) {
		Login theLogin = new Login();
		theModel.addAttribute("login" , theLogin);
		return "register";
	}
	
	
	@GetMapping("/showFormToLogin")
	public String showFormToLogin(Model theModel) {
		Credentials theCredentials = new Credentials();
		theModel.addAttribute("credential" , theCredentials);
		return "login";
	}
		
}
