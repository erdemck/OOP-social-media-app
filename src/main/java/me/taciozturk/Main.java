package me.taciozturk;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import me.taciozturk.Authentication.Login;
import me.taciozturk.Authentication.Register;
import me.taciozturk.Observer.IObserver;
import me.taciozturk.ui.Application;
import me.taciozturk.ui.components.panels.MidPanel;
import me.taciozturk.ui.login.LoginPage;

import java.awt.event.ActionEvent;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.function.LongFunction;

public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {

        String filePath = "src/main/java/me/taciozturk/users.json";

        Register register = new Register(filePath);
        //Login login = new Login();



        FileReader reader = new FileReader(filePath);

        JsonParser parser = new JsonParser();
        JsonArray jsonArray = parser.parse(reader).getAsJsonArray();

        Gson gson = new Gson();
        Type userListType = new TypeToken<List<User>>() {}.getType();
        List<User> users = gson.fromJson(jsonArray, userListType);



        UserList userList = new UserList();
        for (User user : users) {
            userList.add(user);
        }




        LoginPage loginedUser = new LoginPage(userList);//giriş yapan kullanıcı nesnesini döndürüyor



        Group newGroup = new Group("Zehir Tayfa");
        //loginedUser.joinGroup(newGroup);

        UserBuilder ub = new UserBuilder();
        ub.startBuild("mustafa galata", "deneme@gmail.com", "123123")
                .setSearchable(true).build();

        System.out.println(2);

        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                userList.dbUpdate();
                // Program kapanırken yapılacak işlemler buraya yazılır
            }
        });


    }
}