package me.taciozturk;

import java.util.ArrayList;
import java.util.List;

public class UserList {
    private ArrayList<User> users;
    private int id = 1;

    public UserList() {
        this.users = new ArrayList<User>();
    }

    public void add(User user) {
        user.setId();
        this.users.add(user);
        id++;
    }

    public void remove(User user) {
        this.users.remove(user);
    }

    public List<User> getAllUsers() {
        return users;
    }

    public User getUserById(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        };
        return null;
    }

    public void addConnection(User user1, User user2) {
        getUserById(user1.getId()).addConnection(user2);
        getUserById(user2.getId()).addConnection(user1);
    }

    public void removeConnection(User user1, User user2) {
        getUserById(user1.getId()).removeConnection(user2);
        getUserById(user2.getId()).removeConnection(user1);
    }
}
