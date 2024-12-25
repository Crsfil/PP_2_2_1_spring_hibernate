package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   private final SessionFactory sessionFactory;

   public UserDaoImp(SessionFactory sessionFactory) {
      this.sessionFactory = sessionFactory;
   }

   @Transactional
   @Override

   public User getUserByCarModelAndSeries(String model, int series) {
      String hql = "FROM User u JOIN FETCH u.car c WHERE c.model = :model AND c.series = :series";
      return (User) sessionFactory.getCurrentSession()
              .createQuery(hql)
              .setParameter("model", model)
              .setParameter("series", series)
              .uniqueResult();
   }


   @Override
   public void add(User user) {
      if (user.getCar() != null) {
         sessionFactory.getCurrentSession().save(user.getCar());
      }
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

}
