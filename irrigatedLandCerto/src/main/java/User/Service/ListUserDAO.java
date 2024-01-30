package User.Service;

import User.Model.User;
import Util.DAO;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

@Stateless
public class ListUserDAO implements Serializable {

    @PersistenceContext(name = "irrigatedLand")
    private EntityManager manager;

    private DAO<User> dao;

    @PostConstruct
    void init(){
        this.dao = new DAO<User>(this.manager, User.class);
    }

    public void add(User user){
        dao.add(user);
    }

    public void update(User user){
        dao.update(user);
    }

    public void delete(User user){
        dao.remove(user);
    }

    public User searchForId(long id){
        return dao.getForId(id);
    }

    public List<User> listAll(){
        return dao.getAll();
    }

}
