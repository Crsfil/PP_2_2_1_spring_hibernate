package hiber.dao;

import hiber.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserDao {
   @Transactional
   User getUserByCarModelAndSeries(String model, int series);

   void add(User user);
   List<User> listUsers();

}
