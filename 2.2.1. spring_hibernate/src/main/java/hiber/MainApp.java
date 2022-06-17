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

      User user1 = new User("Ash", "Links", "links@mail.com");
      User user2 = new User("Levi", "Akkerman", "levi@mail.com");

      Car car1 = new Car("Ford", 65458);
      Car car2 = new Car("Packard", 89878);

      user1.setCar(car1);
      user2.setCar(car2);

      userService.add(user1);
      userService.add(user2);

      List<User> users2 = userService.listUsers();
      for(User user : users2) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car for user = " +user.getCar());
      }

      System.out.println("Юзер, которго вы искали - " + userService.getUserByModelAndSeries(car1.getModel(), car1.getSeries()));

      context.close();
   }
}
