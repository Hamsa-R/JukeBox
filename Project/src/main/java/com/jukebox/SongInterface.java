package com.jukebox;

import com.userdetails.InvalidLogin;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.sql.SQLException;

public interface SongInterface
{
    public void playMusic(String filepath) throws LineUnavailableException, UnsupportedAudioFileException, IOException, SQLException, InvalidLogin;
}
