package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;


public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("Maria", "Petunova", "user1@mail.ru"),
              new Car("TESLA MODEL S", 24));
      userService.add(new User("Anastasia", "Shkolnikova", "user2@mail.ru"),
              new Car("NISSAN JUKE", 18));
      userService.add(new User("Fred", "Guitarist", "user3@mail.ru"),
              new Car("LADA KALINA", 12));
      userService.add(new User("John", "Doe", "user4@mail.ru"),
              new Car("LADA PRIORA", 20));


      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = "+user.getCar());
         System.out.println();
      }

      User usr = userService.getUserByCarModelAndSeries("TESLA MODEL S", 24);
      System.out.println("тот самый юзер: " + usr.getFirstName() + " " + usr.getLastName());

      context.close();
   }
}
