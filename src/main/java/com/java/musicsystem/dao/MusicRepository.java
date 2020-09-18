package com.java.musicsystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.musicsystem.entity.MusicEntry;

public interface MusicRepository extends JpaRepository<MusicEntry, String>{

}
