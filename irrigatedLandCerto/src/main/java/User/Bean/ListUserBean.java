package User.Bean;

import User.Model.User;
import User.Service.ListUserDAO;
import User.Service.ListUserService;
import Util.Transaction;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class ListUserBean implements Serializable {

    private long userId;

    @Inject
    private ListUserDAO uDao;
    @EJB
    private ListUserService serv;

    private List<User> listUser;
    @ApplicationScoped
    private User user;

    private UserObject userOb = new UserObject();

    @PostConstruct
    void init() {
        createDataTable();
        user = new User();
    }

    private void createDataTable() {
        listUser = serv.getAllUser();
    }

    public List<User> getListUser() {
        return listUser;
    }

    public void setListUser(List<User> listUser) {
        this.listUser = listUser;
    }

    @Transaction
    public String add(){

        if (this.user.getId() == null) {
            this.uDao.add(this.user);
            this.listUser = this.uDao.listAll();
            return "users?faces-redirect=true";
        } else {
            this.uDao.update(this.user);
        }

        this.user = new User();
        return "users?faces-redirect=true";
    }

    @Transaction
    public String update(User user){
        this.user = user;
        return "formularioUser?faces-redirect=true";
    }

    @Transaction
    public void remove(User user){
        uDao.delete(user);
        this.listUser = serv.getAllUser();
    }

    public void searchForId(){
        this.user = this.uDao.searchForId(user.getId());
    }

    public String formApp() {
        System.out.println("Chamanda do formulário da Aplicacao.");
        return "Aplicacao?faces-redirect=true";
    }

    public User getUser() {
        return user;
    }

    public UserObject getUserOb() {
        return userOb;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String newUser(){
        this.user = new User();
        return "formularioUser?faces-redirect=true";
    }

}
