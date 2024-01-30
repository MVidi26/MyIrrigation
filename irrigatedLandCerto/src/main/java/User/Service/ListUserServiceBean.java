package User.Service;

import User.Model.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class ListUserServiceBean implements ListUserService{

    @PersistenceContext(name = "irrigatedLand")
    private EntityManager manager;

    @Override
    public boolean exists(String user, String password) {
        String hql = "SELECT COUNT(u) " +
                "FROM User.Model.User u " +
                "WHERE (u.email = :pUser OR u.name = :pUser) " +
                "AND u.password = :pPass";
        Query query = manager.createQuery(hql);
        query.setParameter("pUser", user);
        query.setParameter("pPass", password);
        List resultList = query.getResultList();
        return !resultList.isEmpty();
    }

    @Override
    public User getUser(String userName, String password) {
        String hql = "SELECT u " +
                "FROM User.Model.User u " +
                "WHERE (u.email = :pUser OR u.name = :pUser) " +
                "AND u.password = :pPass";

        Query query = manager.createQuery(hql);
        query.setParameter("pUser", userName);
        query.setParameter("pPass", password);
        User user = (User) query.getSingleResult();
        if (user != null){
            return user;
        } else {
            return null;
        }
    }

    @Override
    public List<User> getAllUser() {
        List<User> user = new ArrayList<>();

        String hql = "SELECT u FROM User.Model.User u";

        Query query = manager.createQuery(hql);
        List resultList = query.getResultList();
        if(resultList != null && !resultList.isEmpty()) {
            user = resultList;
            return user;
        }
        return user;
    }

    @Override
    public User getByName(String name) {

        String hql = "SELECT u FROM User.Model.User u " +
                "WHERE u.name = :name ";

        return manager.createQuery(hql, User.class)
                .setParameter("name", name)
                .getSingleResult();
    }

}
