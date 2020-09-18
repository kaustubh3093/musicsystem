package com.java.musicsystem.service;

import java.util.List;

import com.java.musicsystem.entity.MusicEntry;

public interface MusicService {

	List<MusicEntry> getAll();
	void save(MusicEntry theMusicEntry);
	void deleteById(String musicId);
	
}
