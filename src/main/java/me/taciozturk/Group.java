package me.taciozturk;

import java.util.ArrayList;

public class Group {
    private String name;
    private ArrayList<User> members;
    private static ArrayList<Group> groups = new ArrayList<>();

    public Group(String name) {
        this.name = name;
        this.members = new ArrayList<>();
        this.groups.add(this);
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public ArrayList<User> getMembers() {
        return members;
    }

    public void addMember(User user) {
        members.add(user);
    }

    public void removeMember(User user) {
        members.remove(user);
    }

    public static ArrayList<Group> getGroups() {
        return groups;
    }
}
