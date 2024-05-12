package me.taciozturk;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class UserBuilder {


    private String name;
    private String email;
    private String hashedPassword;
    private ArrayList<Integer> connections;
    private ArrayList<Group> groups;
    private Boolean searchable;
    private String avatar;
    private ArrayList<Post> posts;



    public static UserBuilder startBuild (String name, String email, String password) throws NoSuchAlgorithmException {
        UserBuilder userBuilder = new UserBuilder();
        userBuilder.setName(name);
        userBuilder.setEmail(email);

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
        userBuilder.setHashedPassword(signHashed);

        return userBuilder;
    }

    public UserBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public UserBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
        return this;
    }

    public UserBuilder setConnections(ArrayList<Integer> connections) {
        this.connections = connections;
        return this;
    }

    public UserBuilder setGroups(ArrayList<Group> groups) {
        this.groups = groups;
        return this;
    }

    public UserBuilder setSearchable(Boolean searchable) {
        this.searchable = searchable;
        return this;
    }

    public UserBuilder setAvatar(String avatar) {
        this.avatar = avatar;
        return this;
    }


    public User build() throws NoSuchAlgorithmException {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setHashedPassword(hashedPassword);
        user.setId();
        user.setSearchable(searchable);
        user.setAvatar(avatar);

        return user;
    }
}
