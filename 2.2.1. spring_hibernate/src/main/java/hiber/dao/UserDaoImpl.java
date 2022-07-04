package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    private SessionFactory sessionFactory;

    final String HQL = "FROM User u LEFT OUTER JOIN FETCH u.car WHERE u.car.model =: modelName and u.car.series =: seriesName";

    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addUser(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public User getUserByModelAndSeries(String model, int series) {

        return (User) sessionFactory.getCurrentSession().createQuery(HQL, User.class).setParameter("modelName", model).setParameter("seriesName", series).uniqueResult();
    }
}
