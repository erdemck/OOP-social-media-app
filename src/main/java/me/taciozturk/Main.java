package me.taciozturk;

import me.taciozturk.Authentication.Login;
import me.taciozturk.Authentication.Register;
import me.taciozturk.ui.Application;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.function.LongFunction;

public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        Register register = new Register("src/main/java/me/taciozturk/users.json");

        UserBuilder userBuilder = new UserBuilder();
        User user = userBuilder.startBuild("Mustafa","mustafa@gmail.com","Mustafa1234.klm3323trkl23")
                        .build();

        register.register(user);

        Login login = new Login();

        User loginedUser = login.login("taci@gmail.com","tac333i1");
        /*
        Application app = new Application();
        app.run(loginedUser);

         */





    }
}