package me.taciozturk.ui;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import me.taciozturk.Authentication.Login;
import me.taciozturk.User;
import me.taciozturk.UserList;
import me.taciozturk.ui.views.UserView;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;


public class Application {
    int SCREEN_WIDTH = 1600;
    int SCREEN_HEIGHT = 920;

    public void run(User user) throws FileNotFoundException, NoSuchAlgorithmException {
        UserList userList = generateUsers(user);
        User user1 = userList.getUserById(1);

        JFrame frame = new JFrame();
        UserView userView = new UserView(SCREEN_WIDTH, SCREEN_HEIGHT, user1);
        frame.add(userView);

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //frame.setUndecorated(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private UserList generateUsers(User user) throws FileNotFoundException {
        UserList list = new UserList();

        //Read the json File
        FileReader reader = new FileReader("/home/erdem/projectoop/social-media-app/src/main/java/me/taciozturk/users.json");

        JsonParser parser = new JsonParser();
        JsonArray jsonArray = parser.parse(reader).getAsJsonArray();

        // JSON to User Object
        Gson gson = new Gson();
        Type userListType = new TypeToken<List<User>>() {}.getType();
        List<User> users = gson.fromJson(jsonArray, userListType);

        for (User user1 : users) {
            list.add(user1);
        }


        return list;
        /*
        UserList userList = new UserList();

        User user1 = new User("Michael Johnson");
        user1.setAvatar("src/main/resources/assets/avatars/man-avatar.png", SCREEN_WIDTH / 6, SCREEN_HEIGHT / 4);
        user1.setEmail("michael.johnson@example.com");
        user1.addPost("It has long been an axiom of mine that the little things are infinitely the most important.");
        user1.addPost("Facing a mirror you see merely your own countenance; facing your child you finally understand how everyone else has seen you.");
        user1.addPost("Everything has got a moral if you can only find it.");
        user1.addPost("Discovery consists of seeing what everybody has seen and thinking what nobody has thought.");
        user1.addPost("An alcoholic is someone you don't like who drinks as much as you do.");
        user1.addPost("Travel is fatal to prejudice, bigotry, and narrow-mindedness, and many of our people need it sorely on these accounts. Broad, wholesome, charitable views of men and things cannot be acquired by vegetating in one little corner of the earth all one's lifetime.");
        user1.addPost("There are painters who transform the sun to a yellow spot, but there are others who with the help of their art and their intelligence, transform a yellow spot into the sun.");
        user1.addPost("Be a craftsman in speech that thou mayest be strong, for the strength of one is the tongue, and speech is mightier than all fighting.");
        user1.addPost("You can discover more about a person in an hour of play than in a year of conversation.");
        user1.addPost("Given a choice between two theories, take the one which is funnier.");
        user1.addPost("Only as you do know yourself can your brain serve you as a sharp and efficient tool. Know your own failings, passions, and prejudices so you can separate them from what you see.");
        user1.addPost("I live now on borrowed time, waiting in the anteroom for the summons that will inevitably come. And then - I go on to the next thing, whatever it is. One doesn't luckily have to bother about that.");
        user1.addPost("When I'm working on a problem, I never think about beauty. I think only how to solve the problem. But when I have finished, if the solution is not beautiful, I know it is wrong.");
        user1.addPost("When I'm working on a problem, I never think about beauty. I think only how to solve the problem. But when I have finished, if the solution is not beautiful, I know it is wrong.");
        user1.addPost("When I'm working on a problem, I never think about beauty. I think only how to solve the problem. But when I have finished, if the solution is not beautiful, I know it is wrong.");
        user1.addPost("When I'm working on a problem, I never think about beauty. I think only how to solve the problem. But when I have finished, if the solution is not beautiful, I know it is wrong.");
        user1.addPost("When I'm working on a problem, I never think about beauty. I think only how to solve the problem. But when I have finished, if the solution is not beautiful, I know it is wrong.");
        user1.addPost("When I'm working on a problem, I never think about beauty. I think only how to solve the problem. But when I have finished, if the solution is not beautiful, I know it is wrong.");
        user1.addPost("When I'm working on a problem, I never think about beauty. I think only how to solve the problem. But when I have finished, if the solution is not beautiful, I know it is wrong.");
        user1.addPost("When I'm working on a problem, I never think about beauty. I think only how to solve the problem. But when I have finished, if the solution is not beautiful, I know it is wrong.");
        user1.addPost("When I'm working on a problem, I never think about beauty. I think only how to solve the problem. But when I have finished, if the solution is not beautiful, I know it is wrong.");
        user1.addPost("When I'm working on a problem, I never think about beauty. I think only how to solve the problem. But when I have finished, if the solution is not beautiful, I know it is wrong.");
        user1.addPost("When I'm working on a problem, I never think about beauty. I think only how to solve the problem. But when I have finished, if the solution is not beautiful, I know it is wrong.");
        user1.addPost("When I'm working on a problem, I never think about beauty. I think only how to solve the problem. But when I have finished, if the solution is not beautiful, I know it is wrong.");
        user1.addPost("When I'm working on a problem, I never think about beauty. I think only how to solve the problem. But when I have finished, if the solution is not beautiful, I know it is wrong.");
        user1.addPost("When I'm working on a problem, I never think about beauty. I think only how to solve the problem. But when I have finished, if the solution is not beautiful, I know it is wrong.");
        user1.addPost("When I'm working on a problem, I never think about beauty. I think only how to solve the problem. But when I have finished, if the solution is not beautiful, I know it is wrong.");
        user1.addPost("When I'm working on a problem, I never think about beauty. I think only how to solve the problem. But when I have finished, if the solution is not beautiful, I know it is wrong.");
        user1.addPost("When I'm working on a problem, I never think about beauty. I think only how to solve the problem. But when I have finished, if the solution is not beautiful, I know it is wrong.");


        User user2 = new User("Bob Smith");
        user2.setAvatar("src/main/resources/assets/avatars/man-avatar-2.png", SCREEN_WIDTH / 6, SCREEN_HEIGHT / 4);
        user2.setEmail("bob.smith@example.com");

        User user3 = new User("Damien White");
        user3.setAvatar("src/main/resources/assets/avatars/man-avatar-3.png", SCREEN_WIDTH / 6, SCREEN_HEIGHT / 4);
        user3.setEmail("damien.white@example.com");

        User user4 = new User("Mary Brown");
        user4.setAvatar("src/main/resources/assets/avatars/woman.png", SCREEN_WIDTH / 6, SCREEN_HEIGHT / 4);
        user4.setEmail("mary.brown@example.com");

        User user5 = new User("Eve Davis");
        user5.setAvatar("src/main/resources/assets/avatars/woman-2.png", SCREEN_WIDTH / 6, SCREEN_HEIGHT / 4);
        user5.setEmail("eve.davis@example.com");

        User user6 = new User("Anne Moore");
        user6.setAvatar("src/main/resources/assets/avatars/woman-3.png", SCREEN_WIDTH / 6, SCREEN_HEIGHT / 4);
        user6.setEmail("anne.moore@example.com");

        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);
        userList.add(user5);
        userList.add(user6);

        userList.addConnection(user1, user2);
        userList.addConnection(user1, user3);
        userList.addConnection(user1, user4);
        userList.addConnection(user1, user5);
        userList.addConnection(user1, user6);

        return userList;
         */


    }
}
