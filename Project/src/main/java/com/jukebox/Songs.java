package com.jukebox;

public class Songs
{

    private int songId;
    private String songName;
    private String duration;
    private String musicDirector;
    private String artists;
    private String language;
    private String songLink;

    public String getSongLink() {
        return songLink;
    }

    public void setSongLink(String songLink) {
        this.songLink = songLink;
    }

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getMusicDirector() {
        return musicDirector;
    }

    public void setMusicDirector(String musicDirector) {
        this.musicDirector = musicDirector;
    }

    public String getArtists() {
        return artists;
    }

    public void setArtists(String artists) {
        this.artists = artists;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Songs(int songId, String songName, String duration, String musicDirector, String artists, String language, String songLink) {
        this.songId = songId;
        this.songName = songName;
        this.duration = duration;
        this.musicDirector = musicDirector;
        this.artists = artists;
        this.language = language;
        this.songLink = songLink;
    }
}
