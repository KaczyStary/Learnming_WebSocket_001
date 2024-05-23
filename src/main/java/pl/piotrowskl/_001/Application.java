package pl.piotrowskl._001;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.piotrowskl._001.model.User;
import pl.piotrowskl._001.service.UserService;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private final UserService userService;

    public Application(UserService userService) {
        this.userService = userService;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        User user = User.builder()
//                .username("user2")
//                .password("user2")
//                .build();
//
//        User user2 = User.builder()
//                .username("user3")
//                .password("user3")
//                .build();
//        userService.saveUser(user);
//        userService.saveUser(user2);
    }
}
