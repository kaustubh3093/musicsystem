package com.java.musicsystem.entity;

public class UsersCount {

	private String title;
	private String artist;
	private int userCount;
	
	public UsersCount() {
		
	}

	public UsersCount(String title, String artist, int userCount) {
		this.title = title;
		this.artist = artist;
		this.userCount = userCount;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
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
		return "Users [title=" + title + ", artist=" + artist + ", userCount=" + userCount + "]";
	}
	
	
}
