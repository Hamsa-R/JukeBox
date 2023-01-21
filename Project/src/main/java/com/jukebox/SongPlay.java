package com.jukebox;

import com.userdetails.InvalidLogin;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class SongPlay implements SongInterface
{
    public static int flag = 0;
    public void playMusic(String filepath) throws LineUnavailableException, UnsupportedAudioFileException, IOException, SQLException, InvalidLogin {
        Scanner sc=new Scanner(System.in);
        File file = new File(filepath);
        Clip clip = AudioSystem.getClip();
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        clip.open(audioStream);
        int input;
        long clippos = 0;
        clip.start();
        System.out.println("-----------------|Enjoy the song|-----------------");
        while(flag==0)
        {
            System.out.println("\n---------------------------------\n\t\tPress\n---------------------------------\n 1. Pause\n 2. Resume\n 3. Restart\n 4. Forward\n 5.Backwards\n6.Return to Main Menu \n  0. Exit");

            input = sc.nextInt();
            switch (input) {
                case 1: {
                    clippos = clip.getMicrosecondPosition();
                    clip.stop();
                    System.out.println("-----------------| II |-----------------");

                }break;
                case 2: {
                    clip.setMicrosecondPosition(clippos);
                    clip.start();
                    System.out.println("-----------------| I> |-----------------");

                }break;
                case 3: {
                    clip.setMicrosecondPosition(0);
                    clip.start();
                    System.out.println("-----------------|Restarted I> |-----------------");

                }break;
                case 4: {
                    System.out.println("-----------------| --->>> |-----------------");
                    clip.setMicrosecondPosition(clip.getMicrosecondPosition() + 5000000);

                }break;
                case 5: {
                    System.out.println("-----------------| <<<--- |-----------------");
                    clip.setMicrosecondPosition(clip.getMicrosecondPosition() - 5000000);

                }break;
                case 6: {
                    clip.close();
                    JukeBox jb = new JukeBox();
                    jb.displayAfterLogin();

                }break;
                case 0:{
                    clip.close();
                    flag = 1;
                    JukeBox.a=0;

                }   break;
                default:
                    System.out.println("___________________");
                    System.out.println("Unexpected value: " + input);
                    System.out.println("___________________");

            }
        }
    }
}
