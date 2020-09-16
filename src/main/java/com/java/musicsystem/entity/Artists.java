package com.java.musicsystem.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Artists {

	@Id
	private String artists;
	private int userCount;
	
	public Artists() {
		
	}

	public Artists(String artists, int userCount) {
		super();
		this.artists = artists;
		this.userCount = userCount;
	}

	/**
	 * @return the artists
	 */
	public String getArtists() {
		return artists;
	}

	/**
	 * @param artists the artists to set
	 */
	public void setArtists(String artists) {
		this.artists = artists;
	}

	/**
	 * @return the userCount
	 */
	public int getUserCount() {
		return userCount;
	}

	/**
	 * @param userCount the userCount to set
	 */
	public void setUserCount(int userCount) {
		this.userCount = userCount;
	}

	@Override
	public String toString() {
		return "Artists [artists=" + artists + ", userCount=" + userCount + "]";
	}
	
	
}
