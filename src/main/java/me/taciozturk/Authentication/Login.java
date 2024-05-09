package me.taciozturk.Authentication;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import me.taciozturk.User;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class Login {

    public Login() {}

    public User login(String email, String password) throws NoSuchAlgorithmException, FileNotFoundException {
        //Read the json File
        FileReader reader = new FileReader("/home/erdem/projectoop/social-media-app/src/main/java/me/taciozturk/users.json");

        // Parsing Json File
        JsonParser parser = new JsonParser();
        JsonArray jsonArray = parser.parse(reader).getAsJsonArray();

        // JSON to User Object
        Gson gson = new Gson();
        Type userListType = new TypeToken<List<User>>() {}.getType();
        List<User> users = gson.fromJson(jsonArray, userListType);


        for (User user : users) {
            if (user.getEmail().equals(email)) {
                if (user.isPasswordValid(password)) {
                    return user;
                }
            }
        }

        System.out.println("Invalid email or password");
        return null;
    }

}

