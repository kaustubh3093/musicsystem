package com.java.musicsystem.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.java.musicsystem.dao.MusicRepository;
import com.java.musicsystem.entity.MusicEntry;

@Service
public class MusicServiceImpl implements MusicService {

	private static final Logger LOG = LoggerFactory.getLogger(MusicServiceImpl.class);
	@Autowired
	private MusicRepository musicRepository;
	
	@Override
	public List<MusicEntry> getAll() {
		LOG.debug("Get the entire record from the Music Entry DB");
		
		return musicRepository.findAll();
	}

	@Override
	public void save(MusicEntry theMusicEntry) {
		LOG.debug("Creating record in the Music Entry database for [{}]", theMusicEntry);
		
		musicRepository.save(theMusicEntry);
	}

	@Override
	public void deleteById(String musicId) {
		LOG.debug("Delete entry from Music Entry database for id [{}]", musicId);
		
		musicRepository.deleteById(musicId);
	}

}
