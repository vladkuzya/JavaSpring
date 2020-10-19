package mate.academy;

import mate.academy.config.AppConfig;
import mate.academy.model.User;
import mate.academy.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = context.getBean(UserService.class);

        userService.add(new User("bob@gmail.com", "12345"));
        userService.add(new User("alica@gmail.com", "1111"));
        userService.add(new User("john@gmail.com", "777"));

        userService.listUsers().forEach(logger::info);
    }
}
