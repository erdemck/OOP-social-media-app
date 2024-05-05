package me.taciozturk;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class User {
    private int id;
    private String name;
    private String email;
    private ArrayList<User> connections;
    private ArrayList<Group> groups;
    private Boolean searchable;
    private ImageIcon avatar;
    private ArrayList<String> posts;

    public User(String name) {
        this.name = name;
        this.connections = new ArrayList<>();
        this.groups = new ArrayList<>();
        this.posts = new ArrayList<>();
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

    public ArrayList<User> getConnections() {
        return connections;
    }

    public void setConnections(ArrayList<User> connections) {
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

    public void setId(int id) {
        this.id = id;
    }

    public ImageIcon getAvatar() {
        return avatar;
    }

    public void setAvatar(String path, int width, int height) {
        this.avatar = new ImageIcon(
                new ImageIcon(path)
                        .getImage()
                        .getScaledInstance(width, height , Image.SCALE_DEFAULT));
    }

    public void addConnection(User user) {
        this.connections.add(user);
    }

    public void removeConnection(User user) {
        this.connections.remove(user);
    }

    public ArrayList<String> getPosts() {
        return posts;
    }

    public void addPost(String post) {
        this.posts.add(post);
    }
}
