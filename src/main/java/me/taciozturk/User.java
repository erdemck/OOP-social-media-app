package me.taciozturk;

import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.security.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class User {
    private static int counter = 0;
    private int id;
    private String name;
    private String email;
    private String hashedPassword;
    private ArrayList<Integer> connections;
    private ArrayList<Group> groups;
    private Boolean searchable;
    private String avatar;
    private ArrayList<Post> posts;

    public User() {
        connections = new ArrayList<Integer>();
        groups = new ArrayList<Group>();
        searchable = false;
        posts = new ArrayList<Post>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getSearchable() {
        return searchable;
    }

    public void setSearchable(Boolean searchable) {
        this.searchable = searchable;
    }

    public ArrayList<Integer> getConnections() {
        return connections;
    }

    public void setConnections(ArrayList<Integer> connections) {
        this.connections = connections;
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<Group> groups) {
        this.groups = groups;
    }

    public int getId() {
        return id;
    }

    public void setId() {
        this.id = counter;
        counter++;
    }


    public ImageIcon getAvatar() {
        return new ImageIcon(
                new ImageIcon(avatar)
                        .getImage()
                        .getScaledInstance(200, 200 , Image.SCALE_DEFAULT));
    }


    public void setAvatar(String path) {
        this.avatar = path;
    }

    public void addConnection(User user) {
        this.connections.add(user.getId());

    }

    public void removeConnection(User user) {
        this.connections.remove(user);
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public void addPost(Post post) {
        this.posts.add(post);
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }


    public boolean isPasswordValid(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = md.digest(password.getBytes());

        // Byte dizisini hexadecimal stringe dönüştürme
        StringBuilder hexString = new StringBuilder();
        for (byte b : hashBytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }

        String signHashed = hexString.toString();
        return this.hashedPassword.equals(signHashed);
    }


}
