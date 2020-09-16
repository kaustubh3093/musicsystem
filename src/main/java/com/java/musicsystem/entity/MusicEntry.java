package com.java.musicsystem.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MusicEntry {

	@Id
	private String id;
	private String user;
	private String song;
	private String artist;
	
	public MusicEntry() {
		
	}

	public MusicEntry(String id, String user, String song, String artist) {
		super();
		this.id = id;
		this.user = user;
		this.song = song;
		this.artist = artist;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * @return the song
	 */
	public String getSong() {
		return song;
	}

	/**
	 * @param song the song to set
	 */
	public void setSong(String song) {
		this.song = song;
	}

	/**
	 * @return the artist
	 */
	public String getArtist() {
		return artist;
	}

	/**
	 * @param artist the artist to set
	 */
	public void setArtist(String artist) {
		this.artist = artist;
	}

	@Override
	public String toString() {
		return "MusicEntry [id=" + id + ", user=" + user + ", song=" + song + ", artist=" + artist + "]";
	}
	
	
}
