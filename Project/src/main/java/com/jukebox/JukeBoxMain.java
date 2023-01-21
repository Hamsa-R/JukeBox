package com.jukebox;

import com.userdetails.InvalidLogin;
import com.userdetails.LogInOrSignIn;
import com.userdetails.User;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.sql.SQLException;
import java.util.List;

public class JukeBoxMain
{
    public static void main(String[] args) throws SQLException, UnsupportedAudioFileException, LineUnavailableException, InvalidLogin {
        LogInOrSignIn log=new LogInOrSignIn();
        List<User> userList=log.getUserDetailsInList();
        log.loginOrSignIn(userList);
        JukeBox jb = new JukeBox();
        jb.displayAfterLogin();
    }
}
