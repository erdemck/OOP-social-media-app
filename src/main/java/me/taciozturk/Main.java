package me.taciozturk;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import me.taciozturk.Authentication.Login;
import me.taciozturk.Authentication.Register;
import me.taciozturk.ui.Application;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.function.LongFunction;

public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {


        Register register = new Register("src/main/java/me/taciozturk/users.json");
        Login login = new Login();



        FileReader reader = new FileReader("/home/erdem/projectoop/social-media-app/src/main/java/me/taciozturk/users.json");

        JsonParser parser = new JsonParser();
        JsonArray jsonArray = parser.parse(reader).getAsJsonArray();

        Gson gson = new Gson();
        Type userListType = new TypeToken<List<User>>() {}.getType();
        List<User> users = gson.fromJson(jsonArray, userListType);

        UserList userList = new UserList();
        for (User user : users) {
            userList.add(user);
        }

        for (int i = 0; i < users.size() - 1 ;i++){
            userList.getUserById(i).addConnection(userList.getUserById(i+1));
        }

        User loginedUser = login.login("chris@example.com","Chris Rodriguez1",userList);

        Application app = new Application();
        app.run(userList,loginedUser);







    }
}