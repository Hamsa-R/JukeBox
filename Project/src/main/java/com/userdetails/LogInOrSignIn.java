package com.userdetails;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class LogInOrSignIn {
    public Statement getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/userDetailsdb", "root", "root@123");
        return connection.createStatement();
    }
    public void newUser() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your Full Name");
        String name = sc.nextLine();
        System.out.println("Enter your EmailId");
        String emailId = sc.nextLine();
        System.out.println("Enter your userId");
        String userId = sc.nextLine();
        System.out.println("Enter your Passward");
        String passward = sc.nextLine();
        Statement statement = getConnection();
        statement.executeUpdate("insert into user values('" + name + "','" + userId + "','" + emailId + "','" + passward + "' )");
        System.out.println("****************************************************************************");
        System.out.println("\t\t\t\tWelcome to JukeBox " + name);
        System.out.println("****************************************************************************");

    }
    public void login(List<User> userList) throws InvalidLogin {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter userId ");
        String userId=sc.nextLine();
        System.out.println("Enter Password");
        String password=sc.nextLine();
        List<User> users=userList;
        Iterator<User> iterator= users.iterator();
        User user;
        while(iterator.hasNext())
        {
            user=iterator.next();

            if(userId.equals(user.getUserId())&&password.equals(user.getPassword()))
            {
                System.out.println("****************************************************************************");
                System.out.println("\t\t\t\tWelcome to JukeBox "+user.getUserFullName());
                System.out.println("****************************************************************************");
                break;
            }
            else
            {
                throw new InvalidLogin("Invalid Login Credentials");
            }
        }
    }
    public List<User> getUserDetailsInList() throws SQLException {
        List<User> userList = new ArrayList<User>();

        ResultSet resultset = getConnection().executeQuery("Select * from user");
        User user = null;
        while (resultset.next()) {
            user = new User(resultset.getString(1), resultset.getString(3), resultset.getString(2), resultset.getString(4));
            userList.add(user);

        }
            return userList;
        }
        public void loginOrSignIn(List<User> userList) throws InvalidLogin, SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the credentials");
        System.out.println("1.New user" + "\n2.Existing User");
        int i = sc.nextInt();
        switch (i) {
            case 1: {
                newUser();
                break;
            }
            case 2: {
                login(userList);
                break;
            }
            default:
                throw new InvalidLogin("Unexpected value: "+i);
        }
    }
}
