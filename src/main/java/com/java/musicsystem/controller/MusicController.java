package com.java.musicsystem.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.java.musicsystem.entity.Artists;
import com.java.musicsystem.entity.Credentials;
import com.java.musicsystem.entity.Login;
import com.java.musicsystem.entity.MusicEntry;
import com.java.musicsystem.entity.UsersCount;
import com.java.musicsystem.service.LoginService;
import com.java.musicsystem.service.MusicService;

@Controller
@RequestMapping("/api")
public class MusicController {

	private static final Logger LOG = LoggerFactory.getLogger(MusicController.class);
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private MusicService musicService;
	
	public static String username;
	
	@RequestMapping("/main")
	public String showMainPage() {
		return "main";
	}
	
	/*Add new credential in the login database
	 * */
	@PostMapping("/save")
	public String saveLogin(@ModelAttribute("login") Login theLogin) {
		LOG.debug("Add new credential into the login database [{}]", theLogin);
		
		List<Login> list = loginService.getAll();
		for(Login login: list) {
			if(login.getUsername().equals(theLogin.getUsername())) {
				LOG.debug("Unable to add new login credential: Username already exists for [{}]", theLogin);
				return "distinctUsername";
			}
		}
		
		LOG.debug("Successfully registered the user for [{}]", theLogin);
		loginService.save(theLogin);
		
		return "redirect:/api/main";
	}
	
	/*
	 * Called When user click on login and the credentials is for normal user
	 * */
	@PostMapping("/addMusicToDB")
	public String addMusicToDB(@ModelAttribute("music") MusicEntry theMusicEntry) {
		LOG.debug("Add new entry into the MusicEntry table [{}]", theMusicEntry);
		
		theMusicEntry.setUser(username);
		List<MusicEntry> musicList = musicService.getAll();
		for(MusicEntry music: musicList) {
			if(music.getUser().equals(username) && music.getArtist().equals(theMusicEntry.getArtist()) && 
							music.getSong().equals(theMusicEntry.getSong()))
				return "redirect:/api/showFormForNormalUser";
		}
		musicService.save(theMusicEntry);
		
		return "redirect:/api/showFormForNormalUser";
	}
	
	/*
	 * Called When user click on login
	 * Will check the validity of the input credentials
	 * Based on the input Credentials it will show you an error page if credential is incorrect
	 * Normal user page is role is user and Admin page if role is Admin for given credentials
	 * */
	@PostMapping("/generateInfo")
	public String generateInfo(@ModelAttribute("credential") Credentials theCredentials) {
		LOG.debug("Check the credentials and see if the user is normal user or admin or incorrect credential for [{}]", theCredentials);
		
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
			LOG.debug("Incorrect Credentials [{}]", theCredentials);
			return "error";
		}
		
		if(admin == false) {
			LOG.debug("Normal User Credentials [{}]", theCredentials);
			return "redirect:/api/showFormForNormalUser";
		}
		
		LOG.debug("Admin User Credentials [{}]", theCredentials);
		return "redirect:/api/showFormForAdminUser";
	}
	
	/*
	 * Called When user click on new user registration link on the main page
	 * */
	@GetMapping("/showFormToRegister")
	public String showFormToRegister(Model theModel) {
		LOG.debug("Show Form to Register new User [{}]");
		
		Login theLogin = new Login();
		theModel.addAttribute("login" , theLogin);
		return "register";
	}
	
	/*
	 * Called When user click on login and the credentials is for normal user
	 * */
	@GetMapping("/showFormForNormalUser")
	public String showFormForNormalUser(Model theModel) {
		LOG.debug("The Given user logged in as a normal user");
		
		List<MusicEntry> musicEntry = new ArrayList<>();
		List<MusicEntry> musicList = musicService.getAll();
		for(MusicEntry m: musicList) {
			if(m.getUser().equals(username)) {
				musicEntry.add(m);
			}
		}
		theModel.addAttribute("music" , musicEntry);
		return "normaluser";
	}
	
	/*
	 * Called When user click on login and the credentials is for admin user
	 * */
	@GetMapping("/showFormForAdminUser")
	public String showFormForAdminUser(Model theModel) {
		LOG.debug("The Given user logged in with admin priviledge");
		
		Map<String, Set<String>> map = new HashMap<>();
		Map<String, Map<String, Set<String>>> songMap = new HashMap<>();
		
		List<MusicEntry> musicList = musicService.getAll();
		for(MusicEntry music: musicList) {
			String song = music.getSong();
			String artist = music.getArtist();
			String user = music.getUser();
			// Update the artist map
			if(!map.containsKey(artist))
				map.put(artist, new HashSet<>());
			map.get(artist).add(user);
			// Update the song map
			if(!songMap.containsKey(song))
				songMap.put(song, new HashMap<>());
			if(!songMap.get(song).containsKey(artist))
				songMap.get(song).put(artist, new HashSet<>());
			songMap.get(song).get(artist).add(user);
		}
		
		List<Artists> uniqueUserArtist = new ArrayList<>();
		for(String key: map.keySet()) {
			Artists artist = new Artists(key, map.get(key).size());
			uniqueUserArtist.add(artist);
		}
		
		List<UsersCount> users = new ArrayList<>();
		for(String s: songMap.keySet()) {
			for(String a: songMap.get(s).keySet()) {
				int count = songMap.get(s).get(a).size();
				UsersCount userCount = new UsersCount(s , a , count);
				users.add(userCount);
			}
		}
		// Bind the Model with the data
		theModel.addAttribute("artists" , uniqueUserArtist);
		theModel.addAttribute("usercount" , users);
		return "admin";
	}
	
	/*
	 * Called When user click on new user registration link on the main page
	 * */
	@GetMapping("/showFormToLogin")
	public String showFormToLogin(Model theModel) {
		LOG.debug("Show the form for login");
		
		Credentials theCredentials = new Credentials();
		theModel.addAttribute("credential" , theCredentials);
		return "login";
	}
		
	/*
	 * Called When normal user click on create new entry and bind the Model with the new Music Entry object
	 * */
	@GetMapping("/createNewEntry")
	public String createNewEntry(Model theModel) {
		LOG.debug("Create new Entry in Music Entry database");
		
		MusicEntry musicEntry = new MusicEntry();
		theModel.addAttribute("music" , musicEntry);
		return "addMusicEntry";
	}
	
	/*
	 * Called When normal user click on to delete specific entry
	 * */
	@GetMapping("/delete")
	public String deleteMusic(@RequestParam("musicId") String musicId) {
		LOG.debug("Delete the entry from Music Entry database for the given music id");
		
		musicService.deleteById(musicId);
		return "redirect:/api/showFormForNormalUser";
	}
}
