package com.jukebox;

import com.userdetails.InvalidLogin;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class JukeBox
{
    public static int a=1;
    public Statement getConnection() throws SQLException {
        Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/userDetailsdb","root","root@123");
        return connection.createStatement();
    }
    public List<Songs> addingSongsInList() throws SQLException {
        ResultSet resultSet = getConnection().executeQuery("Select * from Songs");
        List<Songs> songsList=new ArrayList<Songs>();
        while (resultSet.next()) {
            Songs songs = new Songs(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7));
            songsList.add(songs);
        }
        Comparator<Songs> songIdComparator=((o1,o2)->(o1.getSongId()-o2.getSongId()));
        Collections.sort(songsList,songIdComparator);
        return songsList;
    }
    public void displayAfterLogin() throws SQLException, UnsupportedAudioFileException, LineUnavailableException, InvalidLogin {
        Scanner sc = new Scanner(System.in);
        while (a==1){
            System.out.println("Enter your choice:");
            System.out.println("1.Listen to Songs");
            System.out.println("2.Create Playlist");
            System.out.println("3.Search a song");
            System.out.println("4.View playlists");
            System.out.println("5.Exit");
            int choice = sc.nextInt();
            switch (choice) {
                case 1: {
                    displayAllSongs();
                    System.out.println("Enter songId to play the song");
                    int i = sc.nextInt();
                    toPlaySong(i);
                    break;
                }
                case 2: {
                    createPlayList();
                    System.out.println("To return to main menu press 1");
                    if(sc.nextInt()==1)
                    {
                        displayAfterLogin();
                    }
                    break;
                }
                case 3: {
                    searchSongs();
                    break;
                }
                case 4:
                {
                    viewPlaylist();
                    break;
                }
                case 5:
                {
                    a=0;
                    SongPlay.flag=1;
                    break;
                }
                default:
                    System.out.println("___________________");
                    System.out.println("Unexpected value: " + choice);
                    System.out.println("___________________");
            }
        }
    }

    public void displayAllSongs() throws SQLException {
        List<Songs> s=addingSongsInList();
        Iterator<Songs> iterator= s.iterator();
        System.out.println("***************************************************************************************************");
        System.out.format("%-10s%-20s%-15s%-25s%-20s%-20s","SongId","Name","Duration","MusicDirector","Artists","Language");
        System.out.println();
        System.out.println("***************************************************************************************************");

        while(iterator.hasNext()) {
            Songs song= iterator.next();
            System.out.format("%-10d%-20s%-15s%-25s%-20s%-20s",song.getSongId(),song.getSongName(),song.getDuration(),song.getMusicDirector(),song.getArtists(),song.getLanguage());
            System.out.println();
        }
        System.out.println("***************************************************************************************************");
    }
    public void toPlaySong(int i) throws UnsupportedAudioFileException, LineUnavailableException, SQLException, InvalidLogin {
        if(i==1||i==2||i==3) {
            SongInterface songplay = new SongPlay();
            List<Songs> songsList = addingSongsInList();
            Iterator<Songs> iterator = songsList.iterator();
            String file = null;
            while (iterator.hasNext()) {
                file = iterator.next().getSongLink();
                try {
                    songplay.playMusic(file);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        else {
            System.out.println("___________________");
            System.out.println("Enter Valid input");
            System.out.println("___________________");
        }
    }
    public void searchSongs() throws SQLException, UnsupportedAudioFileException, LineUnavailableException, InvalidLogin {
        Scanner sc=new Scanner(System.in);
        List<Songs> songsList=addingSongsInList();
        int j=0;
        while(j==0) {
            System.out.println("Enter the category you want to search in");
            System.out.println("1.Name\n2.MusicDirector\n3.Artists\n4.Return to Main menu");
            int k = sc.nextInt();
            switch (k) {
                case 1: {
                    Iterator<Songs> iterator= songsList.iterator();
                    while(iterator.hasNext()) {
                        Songs s = iterator.next();
                        System.out.println("SongInterface Name: " + s.getSongName());
                    }
                    System.out.println("Enter song(First word)");
                    String sl = sc.next();
                    Iterator<Songs> iterator1 = songsList.iterator();
                    SongInterface songplay = new SongPlay();
                    while (iterator1.hasNext()) {
                        Songs song = iterator1.next();
                        String a = song.getSongName();
                        if (a.contains(sl)) {
                            System.out.println("SongId: " + song.getSongId() + " , SongInterface Name : " + song.getSongName() + ", Duration: " + song.getDuration() + ", MusicDirector: " + song.getMusicDirector() + ", Artists: " + song.getArtists() + " and Language: " + song.getLanguage());

                            System.out.println("If you want to play the song press 1 else 9");
                            int i = sc.nextInt();
                            if (i == 1) {
                                try {
                                    songplay.playMusic(song.getSongLink());
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }
                    }break;
                }
                case 2:
                {
                    Iterator<Songs> iterator= songsList.iterator();
                    while(iterator.hasNext()) {
                        Songs s = iterator.next();
                        System.out.println("MusicDirector: " + s.getMusicDirector());
                    }
                    System.out.println("Enter MusicDirector(First word) :");
                    String sl = sc.next();
                    Iterator<Songs> iterator1 = songsList.iterator();
                    SongPlay songplay = new SongPlay();
                    while (iterator1.hasNext()) {
                        Songs song = iterator1.next();
                        String a = song.getMusicDirector();
                        if (a.contains(sl)) {
                            System.out.println("SongId: " + song.getSongId() + " , SongInterface Name : " + song.getSongName() + ", Duration: " + song.getDuration() + ", MusicDirector: " + song.getMusicDirector() + ", Artists: " + song.getArtists() + " and Language: " + song.getLanguage());

                            System.out.println("If you want to play the song press 1 else 9");
                            int i = sc.nextInt();
                            if (i == 1) {
                                try {
                                    songplay.playMusic(song.getSongLink());
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }
                    }break;
                }
                case 3:
                {
                    Iterator<Songs> iterator= songsList.iterator();
                    while(iterator.hasNext()) {
                        Songs s = iterator.next();
                        System.out.println("Artist: "+s.getArtists());
                    }
                    System.out.println("Enter Artist(First word) : ");
                    String sl = sc.next();
                    Iterator<Songs> iterator1 = songsList.iterator();
                    SongPlay songplay = new SongPlay();
                    while (iterator1.hasNext()) {
                        Songs song = iterator1.next();
                        String a = song.getArtists();
                        if (a.contains(sl)) {
                            System.out.println("SongId: " + song.getSongId() + " , SongInterface Name : " + song.getSongName() + ", Duration: " + song.getDuration() + ", MusicDirector: " + song.getMusicDirector() + ", Artists: " + song.getArtists() + " and Language: " + song.getLanguage());

                            System.out.println("If you want to play the song press 1 else 9");
                            int i = sc.nextInt();
                            if (i == 1) {
                                try {
                                    songplay.playMusic(song.getSongLink());
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }
                    }break;
                }
                case 4:
                {
                    j=1;
                    break;
                }
            }

        }
    }
    public void addingSongInPlaylist(String userId,String playlistName) throws SQLException {
        Statement statement=getConnection();
        ResultSet rs=statement.executeQuery("Select * from Songs");
        Scanner sc=new Scanner(System.in);
        int songId = 0;
        String songName;
        while(rs.next())
        {
            songId=rs.getInt(1);
            songName=rs.getString(2);
            System.out.println("SongId: "+songId+", SongInterface Name :"+songName);
        }
        System.out.println("Enter songId to add it in playlist");
        int sId=sc.nextInt();
        statement.executeUpdate("insert into playlist values('"+userId+"','"+playlistName+"',"+sId+")");
        System.out.println(playlistName+" playlist is created");
    }
    public void createPlayList() throws SQLException {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter your userId");
        String userId=sc.nextLine();
        System.out.println("Enter Playlist Name");
        String playlistName= sc.next();
        addingSongInPlaylist(userId,playlistName);
        System.out.println("Press 1 to continue adding songs to the Playlist");
        int i=sc.nextInt();
        if(i==1)
        {
            addingSongInPlaylist(userId,playlistName);
        }
    }
    public void viewPlaylist() throws SQLException, UnsupportedAudioFileException, LineUnavailableException, InvalidLogin {
        Statement statement=getConnection();
        Scanner sc = new Scanner(System.in);
        ResultSet rs=statement.executeQuery("Select * from playlist");
        while(rs.next())
        {
            System.out.println("-------------------------------------------");
            System.out.println("Playlist name: "+rs.getString(2)+", SongId: "+rs.getInt(3));
        }
        System.out.println("-------------------------------------------");
        System.out.println("Enter the playlistName");
        String playlistName = sc.nextLine();
        ResultSet rs2 = statement.executeQuery("Select * from playlist where playlistName='" + playlistName + "'");
        while (rs2.next()) {
            System.out.println("-------------------------------------------");
            System.out.println("Playlist name: " + rs2.getString(2) + ", SongId: " + rs2.getInt(3));
        }
        System.out.println("-------------------------------------------");
        System.out.println("Enter song id to play the song else press 9");
        int i = sc.nextInt();
        if (i != 0) {
            toPlaySong(i);
        }

    }
}
